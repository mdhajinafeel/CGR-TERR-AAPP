package com.cgr.codrinterraerp.model.response;

import com.cgr.codrinterraerp.model.response.transactiondata.DispatchContainersResponse;
import com.cgr.codrinterraerp.model.response.transactiondata.FarmInventoryOrdersResponse;
import com.cgr.codrinterraerp.model.response.transactiondata.ReceptionDetailsResponse;
import com.cgr.codrinterraerp.model.response.transactiondata.ReceptionInventoryOrdersResponse;

import java.io.Serializable;
import java.util.List;

public class DownloadTransactionsDataResponse implements Serializable {

    private List<FarmInventoryOrdersResponse> farmInventoryOrders;
    private List<ReceptionInventoryOrdersResponse> receptionInventoryOrders;
    private List<DispatchContainersResponse> dispatchContainers;
    private List<ReceptionDetailsResponse> receptionDetails;

    public List<FarmInventoryOrdersResponse> getFarmInventoryOrders() {
        return farmInventoryOrders;
    }

    public void setFarmInventoryOrders(List<FarmInventoryOrdersResponse> farmInventoryOrders) {
        this.farmInventoryOrders = farmInventoryOrders;
    }

    public List<ReceptionInventoryOrdersResponse> getReceptionInventoryOrders() {
        return receptionInventoryOrders;
    }

    public void setReceptionInventoryOrders(List<ReceptionInventoryOrdersResponse> receptionInventoryOrders) {
        this.receptionInventoryOrders = receptionInventoryOrders;
    }

    public List<DispatchContainersResponse> getDispatchContainers() {
        return dispatchContainers;
    }

    public void setDispatchContainers(List<DispatchContainersResponse> dispatchContainers) {
        this.dispatchContainers = dispatchContainers;
    }

    public List<ReceptionDetailsResponse> getReceptionDetails() {
        return receptionDetails;
    }

    public void setReceptionDetails(List<ReceptionDetailsResponse> receptionDetails) {
        this.receptionDetails = receptionDetails;
    }
}