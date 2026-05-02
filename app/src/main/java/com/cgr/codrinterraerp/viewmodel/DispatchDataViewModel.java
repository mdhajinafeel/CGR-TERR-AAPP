package com.cgr.codrinterraerp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.cgr.codrinterraerp.model.ContainerWithReception;
import com.cgr.codrinterraerp.repository.DispatchDataRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DispatchDataViewModel extends ViewModel {

    private final DispatchDataRepository dispatchDataRepository;

    @Inject
    public DispatchDataViewModel(DispatchDataRepository dispatchDataRepository) {
        this.dispatchDataRepository = dispatchDataRepository;
    }

    public List<ContainerWithReception> fetchContainerData(Integer dispatchId, String tempDispatchId) {
        return dispatchDataRepository.fetchContainerData(dispatchId, tempDispatchId);
    }
}