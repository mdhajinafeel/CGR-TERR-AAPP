package com.cgr.codrinterraerp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.cgr.codrinterraerp.db.entities.DispatchContainers;
import com.cgr.codrinterraerp.db.entities.DispatchDetails;
import com.cgr.codrinterraerp.db.views.DispatchView;
import com.cgr.codrinterraerp.repository.DispatchRepository;
import com.cgr.codrinterraerp.wrapper.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DispatchViewModel extends ViewModel {

    private DispatchRepository dispatchRepository;
    private long dispatchSavedId;
    private final SingleLiveEvent<Boolean> dispatchStatus = new SingleLiveEvent<>();
    private final SingleLiveEvent<Boolean> progressState = new SingleLiveEvent<>();
    private final MutableLiveData<Boolean> dispatchDataTrigger = new MutableLiveData<>();

    @Inject
    public DispatchViewModel(DispatchRepository dispatchRepository) {
        this.dispatchRepository = dispatchRepository;
    }

    public void saveDispatchDetails(DispatchDetails dispatchDetails) {
        progressState.postValue(true);
        long dispatch = dispatchRepository.saveDispatchDetails(dispatchDetails);
        progressState.postValue(false);
        if(dispatch > 0) {

            DispatchContainers dispatchContainers = new DispatchContainers();
            dispatchContainers.setContainerNumber(dispatchDetails.getContainerNumber());
            dispatchContainers.setShippingLineId(dispatchDetails.getShippingLineId());

            setDispatchSavedId(dispatch);
            dispatchStatus.postValue(true);
        } else {
            setDispatchSavedId(0);
            dispatchStatus.postValue(false);
        }
    }

    public int getDispatchContainersCount(String containerNumber, int shippingLineId) {
        return dispatchRepository.getDispatchContainersCount(containerNumber, shippingLineId);
    }

    private final LiveData<List<DispatchView>> dispatchList =
            Transformations.switchMap(dispatchDataTrigger, input ->
                    dispatchRepository.getDispatchList()
            );

    public LiveData<List<DispatchView>> getDispatchList() {
        return dispatchList;
    }

    public void load() {
        dispatchDataTrigger.setValue(true);
    }

    public LiveData<Boolean> getProgressState() {
        return progressState;
    }

    public LiveData<Boolean> getDispatchStatus() {
        return dispatchStatus;
    }

    public long getDispatchSavedId() {
        return dispatchSavedId;
    }

    public void setDispatchSavedId(long dispatchSavedId) {
        this.dispatchSavedId = dispatchSavedId;
    }
}