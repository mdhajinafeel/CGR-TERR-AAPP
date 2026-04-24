package com.cgr.codrinterraerp.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cgr.codrinterraerp.R;
import com.cgr.codrinterraerp.db.entities.ReceptionDetails;
import com.cgr.codrinterraerp.db.views.ReceptionView;
import com.cgr.codrinterraerp.repository.ReceptionRepository;
import com.cgr.codrinterraerp.wrapper.SingleLiveEvent;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;

@HiltViewModel
public class ReceptionViewModel extends ViewModel {

    private final ReceptionRepository receptionRepository;
    private final Context context;
    private String errorTitle, errorMessage;
    private long receptionSavedId;
    private final SingleLiveEvent<Boolean> receptionStatus = new SingleLiveEvent<>();
    private final SingleLiveEvent<Boolean> progressState = new SingleLiveEvent<>();
    public LiveData<List<ReceptionView>> receptionList;

    @Inject
    public ReceptionViewModel(ReceptionRepository receptionRepository, @ApplicationContext Context context) {
        this.receptionRepository = receptionRepository;
        this.context = context;
    }

    public void saveReceptionDetails(ReceptionDetails receptionDetails) {
        progressState.postValue(true);
        long reception = receptionRepository.saveReceptionDetails(receptionDetails);
        progressState.postValue(false);
        if(reception > 0) {
            setReceptionSavedId(reception);
            receptionStatus.postValue(true);
        } else {
            setReceptionSavedId(0);
            receptionStatus.postValue(false);
            setErrorTitle(context.getString(R.string.error));
            setErrorMessage(context.getString(R.string.common_error));
        }
    }

    public int getReceptionInventoryOrdersCount(String inventoryOrder, int supplierId) {
        return receptionRepository.getReceptionInventoryOrdersCount(inventoryOrder, supplierId);
    }

    public LiveData<List<ReceptionView>> getReceptionList() {
        if (receptionList == null) {
            receptionList = receptionRepository.getReceptionList();
        }
        return receptionList;
    }

    public LiveData<Boolean> getProgressState() {
        return progressState;
    }

    public LiveData<Boolean> getReceptionStatus() {
        return receptionStatus;
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

    public long getReceptionSavedId() {
        return receptionSavedId;
    }

    public void setReceptionSavedId(long receptionSavedId) {
        this.receptionSavedId = receptionSavedId;
    }
}