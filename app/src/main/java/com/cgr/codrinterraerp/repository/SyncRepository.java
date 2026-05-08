package com.cgr.codrinterraerp.repository;

import com.cgr.codrinterraerp.db.dao.SyncDao;
import com.cgr.codrinterraerp.db.entities.ContainerImages;
import com.cgr.codrinterraerp.model.request.SyncRequest;
import com.cgr.codrinterraerp.model.response.IdMappingResponse;
import com.cgr.codrinterraerp.model.response.ImageUploadResponse;
import com.cgr.codrinterraerp.model.response.SyncResponse;
import com.cgr.codrinterraerp.services.ISyncApiService;
import com.cgr.codrinterraerp.utils.AppLogger;

import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class SyncRepository {

    private final SyncDao syncDao;
    private final ISyncApiService iSyncApiService;

    public SyncRepository(SyncDao syncDao, ISyncApiService iSyncApiService) {
        this.syncDao = syncDao;
        this.iSyncApiService = iSyncApiService;
    }

    // =====================
    // MAIN IMAGE SYNC
    // =====================
    public void syncContainerPhotos() {
        List<ContainerImages> images = syncDao.getUnsyncedImages();
        for (ContainerImages img : images) {
            uploadContainerImage(img);
        }
    }

    // =====================
    // SINGLE IMAGE UPLOAD
    // =====================

    private void uploadContainerImage(ContainerImages img) {
        try {

            // =================
            // FILE CHECK
            // =================
            File file = new File(img.imagePath);
            if (!file.exists()) {
                syncDao.markFailed(img.tempContainerImageId);
                return;
            }

            // =================
            // REQUEST BODY
            // =================
            RequestBody reqFile = RequestBody.create(file, MediaType.parse("image/*"));

            MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), reqFile);

            RequestBody tempImageId = RequestBody.create(img.tempContainerImageId, MultipartBody.FORM);
            RequestBody tempDispatchId = RequestBody.create(img.tempDispatchId, MultipartBody.FORM);

            // =================
            // API CALL
            // =================
            Response<ImageUploadResponse> response = iSyncApiService.uploadContainerPhoto(body, tempImageId, tempDispatchId).execute();

            // =================
            // SUCCESS
            // =================
            if (response.isSuccessful() && response.body() != null && response.body().status) {
                syncDao.updateImageSync(response.body().tempContainerImageId, response.body().url);
            } else {
                syncDao.markFailed(img.tempContainerImageId);
            }
        } catch (SocketTimeoutException e) {
            syncDao.markFailed(img.tempContainerImageId);
            AppLogger.e(getClass(), "uploadSingleImage", e);
        } catch (IOException e) {
            syncDao.markFailed(img.tempContainerImageId);
            AppLogger.e(getClass(), "IOException", e);
        } catch (Exception e) {
            syncDao.markFailed(img.tempContainerImageId);
            AppLogger.e(getClass(), "Exception", e);
        }
    }

    // =====================
    // MAIN SYNC
    // =====================
    public SyncResponse syncEverything() {
        SyncResponse syncResponse = new SyncResponse();
        try {
            // =================
            // STEP 1
            // IMAGE UPLOAD
            // =================
            syncContainerPhotos();

            // =================
            // STEP 2
            // MASTER DATA
            // =================
            SyncRequest request = new SyncRequest();
            request.receptionDetails = syncDao.getUnsyncedReceptionDetails();
            request.receptionData = syncDao.getUnsyncedReceptionData();
            request.dispatchDetails = syncDao.getUnsyncedDispatch();
            request.containerData = syncDao.getUnsyncedContainerData();

            // =================
            // API CALL
            // =================
            Response<SyncResponse> response = iSyncApiService.syncData(request).execute();

            if (response.isSuccessful() && response.body() != null && response.body().status) {
                SyncResponse res = response.body();

                // RECEPTION
                for (IdMappingResponse map : res.receptionMappings) {
                    syncDao.updateReceptionMapping(map.tempId, map.serverId);
                }

                // RECEPTION DATA
                for (IdMappingResponse map : res.receptionDataMappings) {
                    syncDao.updateReceptionDataMapping(map.tempId, map.serverId);
                }

                // DISPATCH
                for (IdMappingResponse map : res.dispatchMappings) {
                    syncDao.updateDispatchMapping(map.tempId, map.serverId);
                }

                syncDao.markContainerDataSynced();

                syncResponse.status = true;
                syncResponse.message = "Sync Completed";
            } else {
                syncResponse.status = false;
                syncResponse.message = "Failed to sync";
            }
        } catch (Exception e) {
            syncResponse.status = false;
            syncResponse.message = e.getMessage();
            AppLogger.e(getClass(), "Exception", e);
        }

        return syncResponse;
    }
}