package com.cgr.codrinterraerp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.cgr.codrinterraerp.db.entities.ApiLogs;
import com.cgr.codrinterraerp.repository.AppStatusRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AppStatusViewModel extends ViewModel {

    private AppStatusRepository appStatusRepository;
    private final MutableLiveData<Boolean> apiLogsTrigger = new MutableLiveData<>();

    @Inject
    public AppStatusViewModel(AppStatusRepository appStatusRepository) {
        this.appStatusRepository = appStatusRepository;
    }

    private final LiveData<List<ApiLogs>> apiLogsList =
            Transformations.switchMap(apiLogsTrigger, input ->
                    appStatusRepository.getAllApiLogs()
            );

    public LiveData<List<ApiLogs>> getApiLogsList() {
        return apiLogsList;
    }

    public void load() {
        apiLogsTrigger.setValue(true);
    }
}