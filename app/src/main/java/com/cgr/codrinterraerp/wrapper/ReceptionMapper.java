package com.cgr.codrinterraerp.wrapper;

import androidx.annotation.NonNull;

import com.cgr.codrinterraerp.db.entities.ReceptionData;
import com.cgr.codrinterraerp.db.entities.ReceptionDetails;
import com.cgr.codrinterraerp.model.response.transactiondata.ReceptionDataResponse;
import com.cgr.codrinterraerp.model.response.transactiondata.ReceptionDetailsResponse;

import java.util.ArrayList;
import java.util.List;

public class ReceptionMapper {

    // =====================================================
    // WRAPPER CLASS
    // =====================================================
    public record ReceptionSyncResult(List<ReceptionDetails> receptionDetailsList,
                                      List<ReceptionData> receptionDataList) {
    }

    // =====================================================
    // MAIN MAPPER
    // =====================================================
    public static ReceptionSyncResult getReceptionDetails(List<ReceptionDetailsResponse> receptionDetail) {

        List<ReceptionDetails> receptionDetailsList = new ArrayList<>();
        List<ReceptionData> receptionDataList = new ArrayList<>();

        if (receptionDetail == null || receptionDetail.isEmpty()) {
            return new ReceptionSyncResult(receptionDetailsList, receptionDataList);
        }

        // =================================================
        // LOOP RESPONSE
        // =================================================
        for (ReceptionDetailsResponse response : receptionDetail) {

            // =============================================
            // RECEPTION DETAILS
            // =============================================
            ReceptionDetails receptionDetails = getReceptionDetails(response);

            receptionDetailsList.add(receptionDetails);

            // =============================================
            // RECEPTION DATA
            // =============================================
            if (response.getReceptionData() != null &&
                    !response.getReceptionData().isEmpty()) {

                for (ReceptionDataResponse dataResponse :
                        response.getReceptionData()) {

                    ReceptionData receptionData = getReceptionData(dataResponse);

                    receptionDataList.add(receptionData);
                }
            }
        }

        return new ReceptionSyncResult(receptionDetailsList, receptionDataList);
    }

    @NonNull
    private static ReceptionData getReceptionData(ReceptionDataResponse dataResponse) {
        ReceptionData receptionData = new ReceptionData();
        receptionData.setReceptionDataId(dataResponse.getReceptionDataId());
        receptionData.setReceptionId(dataResponse.getReceptionId());
        receptionData.setCircumference(dataResponse.getCircumference());
        receptionData.setLength(dataResponse.getLength());
        receptionData.setThickness(dataResponse.getThickness());
        receptionData.setWidth(dataResponse.getWidth());
        receptionData.setPieces(dataResponse.getPieces());
        receptionData.setGrossVolume(dataResponse.getGrossVolume());
        receptionData.setNetVolume(dataResponse.getNetVolume());
        receptionData.setVolumePie(dataResponse.getVolumePie());
        receptionData.setCreatedAt(dataResponse.getCreatedAt());
        receptionData.setUpdatedAt(dataResponse.getUpdatedAt());
        receptionData.setTempReceptionDataId(dataResponse.getTempReceptionDataId());
        receptionData.setTempReceptionId(dataResponse.getTempReceptionId());
        receptionData.setContainerReceptionMappingId(dataResponse.getContainerReceptionMappingId());

        // LOCAL FLAGS
        receptionData.setSynced(true);
        receptionData.setDeleted(false);
        receptionData.setEdited(false);
        return receptionData;
    }

    @NonNull
    private static ReceptionDetails getReceptionDetails(ReceptionDetailsResponse response) {
        ReceptionDetails receptionDetails = new ReceptionDetails();
        receptionDetails.setTempReceptionId(response.getTempReceptionId());
        receptionDetails.setReceptionId(response.getReceptionId());
        receptionDetails.setIca(response.getIca());
        receptionDetails.setSupplierId(response.getSupplierId());
        receptionDetails.setSupplierProductId(response.getSupplierProductId());
        receptionDetails.setProductId(response.getProductId());
        receptionDetails.setSupplierProductTypeId(response.getSupplierProductTypeId());
        receptionDetails.setProductTypeId(response.getProductTypeId());
        receptionDetails.setMeasurementSystem(response.getMeasurementSystemId());
        receptionDetails.setWarehouse(response.getWarehouseId());
        receptionDetails.setReceptionDate(response.getReceivedDate());
        receptionDetails.setFarmEnabled(response.isCreateFarm());
        receptionDetails.setPurchaseContract(response.getContractId());
        receptionDetails.setTruckNumber(response.getTruckPlateNumber());
        receptionDetails.setTruckDriverName(response.getTruckDriverName());
        receptionDetails.setContainerReceptionMappingId(response.getContainerReceptionMappingId());
        receptionDetails.setClosed(response.isClosed());
        receptionDetails.setClosedBy(response.getClosedBy());
        receptionDetails.setClosedDate(response.getClosedDate());
        receptionDetails.setCreatedAt(response.getCapturedTimestamp());
        receptionDetails.setUpdatedAt(response.getUpdatedAt());

        // LOCAL FLAGS
        receptionDetails.setSynced(true);
        receptionDetails.setDeleted(false);
        receptionDetails.setEdited(false);
        return receptionDetails;
    }
}