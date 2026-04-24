package com.cgr.codrinterraerp.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cgr.codrinterraerp.R;
import com.cgr.codrinterraerp.db.entities.DispatchDetails;
import com.cgr.codrinterraerp.repository.DispatchRepository;
import com.cgr.codrinterraerp.wrapper.SingleLiveEvent;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;

@HiltViewModel
public class DispatchViewModel extends ViewModel {

    private final DispatchRepository dispatchRepository;
    private final Context context;
    private String errorTitle, errorMessage;
    private long dispatchSavedId;
    private final SingleLiveEvent<Boolean> dispatchStatus = new SingleLiveEvent<>();
    private final SingleLiveEvent<Boolean> progressState = new SingleLiveEvent<>();

    @Inject
    public DispatchViewModel(DispatchRepository dispatchRepository, @ApplicationContext Context context) {
        this.dispatchRepository = dispatchRepository;
        this.context = context;
    }

    public void saveDispatchDetails(DispatchDetails dispatchDetails) {
        progressState.postValue(true);
        long dispatch = dispatchRepository.saveDispatchDetails(dispatchDetails);
        progressState.postValue(false);
        if(dispatch > 0) {
            setDispatchSavedId(dispatch);
            dispatchStatus.postValue(true);
        } else {
            setDispatchSavedId(0);
            dispatchStatus.postValue(false);
            setErrorTitle(context.getString(R.string.error));
            setErrorMessage(context.getString(R.string.common_error));
        }
    }

    public int getDispatchContainersCount(String containerNumber, int shippingLineId) {
        return dispatchRepository.getDispatchContainersCount(containerNumber, shippingLineId);
    }

    public LiveData<Boolean> getProgressState() {
        return progressState;
    }

    public LiveData<Boolean> getDispatchStatus() {
        return dispatchStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorTitle() {
        return errorTitle;
    }

    public void setErrorTitle(String errorTitle) {
        this.errorTitle = errorTitle;
    }

    public long getDispatchSavedId() {
        return dispatchSavedId;
    }

    public void setDispatchSavedId(long dispatchSavedId) {
        this.dispatchSavedId = dispatchSavedId;
    }
}