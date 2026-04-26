package com.cgr.codrinterraerp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.cgr.codrinterraerp.db.entities.FarmInventoryOrders;
import com.cgr.codrinterraerp.db.entities.ReceptionDetails;
import com.cgr.codrinterraerp.db.entities.ReceptionInventoryOrders;
import com.cgr.codrinterraerp.db.views.ReceptionView;
import com.cgr.codrinterraerp.repository.ReceptionRepository;
import com.cgr.codrinterraerp.wrapper.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ReceptionViewModel extends ViewModel {

    private ReceptionRepository receptionRepository;
    private long receptionSavedId;
    private final SingleLiveEvent<Boolean> receptionStatus = new SingleLiveEvent<>();
    private final SingleLiveEvent<Boolean> progressState = new SingleLiveEvent<>();
    private final MutableLiveData<Boolean> receptionDataTrigger = new MutableLiveData<>();

    @Inject
    public ReceptionViewModel(ReceptionRepository receptionRepository) {
        this.receptionRepository = receptionRepository;
    }

    public void saveReceptionDetails(ReceptionDetails receptionDetails) {
        progressState.postValue(true);
        long reception = receptionRepository.saveReceptionDetails(receptionDetails);
        progressState.postValue(false);
        if(reception > 0) {

            ReceptionInventoryOrders receptionInventoryOrders = new ReceptionInventoryOrders();
            receptionInventoryOrders.setInventoryOrder(receptionDetails.getIca());
            receptionInventoryOrders.setSupplierId(receptionDetails.getSupplierId());
            receptionRepository.insertReceptionInventoryOrder(receptionInventoryOrders);

            if(receptionDetails.isFarmEnabled) {
                FarmInventoryOrders farmInventoryOrders = new FarmInventoryOrders();
                farmInventoryOrders.setInventoryOrder(receptionDetails.getIca());
                farmInventoryOrders.setSupplierId(receptionDetails.getSupplierId());
                receptionRepository.insertFarmInventoryOrder(farmInventoryOrders);
            }

            setReceptionSavedId(reception);
            receptionStatus.postValue(true);
        } else {
            setReceptionSavedId(0);
            receptionStatus.postValue(false);
        }
    }

    public int getReceptionInventoryOrdersCount(String inventoryOrder, int supplierId) {
        return receptionRepository.getReceptionInventoryOrdersCount(inventoryOrder, supplierId);
    }

    private final LiveData<List<ReceptionView>> receptionList =
            Transformations.switchMap(receptionDataTrigger, input ->
                    receptionRepository.getReceptionList()
            );

    public LiveData<List<ReceptionView>> getReceptionList() {
        return receptionList;
    }

    public void load() {
        receptionDataTrigger.setValue(true);
    }

    public LiveData<Boolean> getProgressState() {
        return progressState;
    }

    public LiveData<Boolean> getReceptionStatus() {
        return receptionStatus;
    }

    public long getReceptionSavedId() {
        return receptionSavedId;
    }

    public void setReceptionSavedId(long receptionSavedId) {
        this.receptionSavedId = receptionSavedId;
    }
}