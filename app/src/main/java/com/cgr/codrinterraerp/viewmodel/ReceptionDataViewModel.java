package com.cgr.codrinterraerp.viewmodel;

import androidx.lifecycle.ViewModel;

import com.cgr.codrinterraerp.db.entities.MeasurementSystemFormulas;
import com.cgr.codrinterraerp.db.relations.FormulaWithVariables;
import com.cgr.codrinterraerp.repository.ReceptionDataRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ReceptionDataViewModel extends ViewModel {

    private final ReceptionDataRepository receptionDataRepository;

    @Inject
    public ReceptionDataViewModel(ReceptionDataRepository receptionDataRepository) {
        this.receptionDataRepository = receptionDataRepository;
    }

    public FormulaWithVariables getFormulasWithVariables(int measurementSystemId) {
        return receptionDataRepository.getFormulasWithVariables(measurementSystemId);
    }
}