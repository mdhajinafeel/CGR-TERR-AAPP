package com.cgr.codrinterraerp.wrapper;

import androidx.annotation.NonNull;

import com.cgr.codrinterraerp.db.entities.ContainerData;
import com.cgr.codrinterraerp.db.entities.DispatchDetails;
import com.cgr.codrinterraerp.model.response.transactiondata.ContainerDataResponse;
import com.cgr.codrinterraerp.model.response.transactiondata.DispatchDetailsResponse;

import java.util.ArrayList;
import java.util.List;

public class DispatchMapper {

    // =====================================================
    // WRAPPER CLASS
    // =====================================================
    public record DispatchSyncResult(List<DispatchDetails> dispatchDetailsList,
                                      List<ContainerData> containerDataList) {
    }

    // =====================================================
    // MAIN MAPPER
    // =====================================================
    public static DispatchSyncResult getDispatchDetails(List<DispatchDetailsResponse> dispatchDetail) {

        List<DispatchDetails> dispatchDetailsList = new ArrayList<>();
        List<ContainerData> containerDataList = new ArrayList<>();

        if (dispatchDetail == null || dispatchDetail.isEmpty()) {
            return new DispatchSyncResult(dispatchDetailsList, containerDataList);
        }

        // =================================================
        // LOOP RESPONSE
        // =================================================
        for (DispatchDetailsResponse response : dispatchDetail) {

            // =============================================
            // DISPATCH DETAILS
            // =============================================
            DispatchDetails dispatchDetails = getDispatchDetails(response);
            dispatchDetailsList.add(dispatchDetails);

            // =============================================
            // CONTAINER DATA
            // =============================================
            if (response.getContainerData() != null &&
                    !response.getContainerData().isEmpty()) {

                for (ContainerDataResponse dataResponse :
                        response.getContainerData()) {

                    ContainerData containerData = getContainerData(dataResponse);
                    containerDataList.add(containerData);
                }
            }
        }

        return new DispatchSyncResult(dispatchDetailsList, containerDataList);
    }

    @NonNull
    private static ContainerData getContainerData(ContainerDataResponse dataResponse) {
        ContainerData containerData = new ContainerData();
        containerData.setTempDispatchDataId(dataResponse.getTempDispatchDataId());
        containerData.setDispatchDataId(dataResponse.getDispatchDataId());
        containerData.setTempReceptionDataId(dataResponse.getTempReceptionDataId());
        containerData.setTempDispatchId(dataResponse.getTempDispatchId());
        containerData.setReceptionDataId(dataResponse.getReceptionDataId());
        containerData.setDispatchId(dataResponse.getDispatchId());
        containerData.setTempReceptionId(dataResponse.getTempReceptionId());
        containerData.setReceptionDataId(dataResponse.getReceptionDataId());
        containerData.setReceptionId(dataResponse.getReceptionId());
        containerData.setPieces(dataResponse.getPieces());
        containerData.setGrossVolume(dataResponse.getGrossVolume());
        containerData.setNetVolume(dataResponse.getNetVolume());
        containerData.setVolumePie(dataResponse.getVolumePie());
        containerData.setCreatedAt(dataResponse.getCreatedAt());
        containerData.setUpdatedAt(dataResponse.getUpdatedAt());
        containerData.setContainerReceptionMappingId(dataResponse.getContainerReceptionMappingId());

        // LOCAL FLAGS
        containerData.setSynced(true);
        containerData.setDeleted(false);
        containerData.setEdited(false);
        return containerData;
    }

    @NonNull
    private static DispatchDetails getDispatchDetails(DispatchDetailsResponse response) {
        DispatchDetails dispatchDetails = new DispatchDetails();
        dispatchDetails.setTempDispatchId(response.getTempDispatchId());
        dispatchDetails.setDispatchId(response.getDispatchId());
        dispatchDetails.setContainerNumber(response.getContainerNumber());
        dispatchDetails.setProductId(response.getProductId());
        dispatchDetails.setProductTypeId(response.getProductTypeId());
        dispatchDetails.setWarehouseId(response.getWarehouseId());
        dispatchDetails.setShippingLineId(response.getShippingLineId());
        dispatchDetails.setDispatchDate(response.getDispatchDate());
        dispatchDetails.setCategoryId(response.getCategoryId());
        dispatchDetails.setClosed(response.isClosed());
        dispatchDetails.setClosedBy(response.getClosedBy());
        dispatchDetails.setClosedDate(response.getClosedDate());
        dispatchDetails.setCreatedAt(response.getCreatedAt());
        dispatchDetails.setUpdatedAt(response.getUpdatedAt());

        // LOCAL FLAGS
        dispatchDetails.setSynced(true);
        dispatchDetails.setDeleted(false);
        dispatchDetails.setEdited(false);
        return dispatchDetails;
    }
}