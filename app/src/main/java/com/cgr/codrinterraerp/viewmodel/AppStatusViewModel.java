package com.cgr.codrinterraerp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.cgr.codrinterraerp.db.entities.ApiLogs;
import com.cgr.codrinterraerp.model.LogCount;
import com.cgr.codrinterraerp.repository.AppStatusRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AppStatusViewModel extends ViewModel {

    private final AppStatusRepository appStatusRepository;

    private final MutableLiveData<String> filterType =
            new MutableLiveData<>("ALL");

    private final LiveData<List<ApiLogs>> apiLogsList;

    @Inject
    public AppStatusViewModel(AppStatusRepository appStatusRepository) {

        this.appStatusRepository = appStatusRepository;

        apiLogsList =
                Transformations.switchMap(filterType, type -> {

                    if ("ALL".equalsIgnoreCase(type)) {
                        return this.appStatusRepository.getAllApiLogs();
                    }

                    return this.appStatusRepository.getApiLogsByType(type);
                });
    }

    public LiveData<List<ApiLogs>> getApiLogsList() {
        return apiLogsList;
    }

    public void setFilter(String type) {
        filterType.setValue(type);
    }

    public void clearLogs(String type) {
        appStatusRepository.clearLogsByType(type);
    }

    public void clearLog(int id, long createdAt) {
        appStatusRepository.clearLog(id, createdAt);
    }

    public LiveData<List<LogCount>> getLogCounts() {
        return appStatusRepository.getLogCounts();
    }
}