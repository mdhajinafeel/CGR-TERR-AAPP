package com.cgr.codrinterraerp.model.response;

import com.cgr.codrinterraerp.model.response.masterdata.DispatchContainersResponse;
import com.cgr.codrinterraerp.model.response.masterdata.FarmInventoryOrdersResponse;
import com.cgr.codrinterraerp.model.response.masterdata.ReceptionInventoryOrdersResponse;

import java.io.Serializable;
import java.util.List;

public class DownloadTransactionsDataResponse implements Serializable {

    private List<FarmInventoryOrdersResponse> farmInventoryOrders;
    private List<ReceptionInventoryOrdersResponse> receptionInventoryOrders;
    private List<DispatchContainersResponse> dispatchContainers;

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
}