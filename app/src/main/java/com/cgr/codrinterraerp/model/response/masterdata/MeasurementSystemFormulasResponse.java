package com.cgr.codrinterraerp.model.response.masterdata;

import java.io.Serializable;
import java.util.List;

public class MeasurementSystemFormulasResponse implements Serializable {

    private String formula, roundingType, context;
    private int formulaMasterId, roundPrecision;
    private List<MeasurementSystemFormulaVariablesResponse> variables;

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public int getRoundPrecision() {
        return roundPrecision;
    }

    public void setRoundPrecision(int roundPrecision) {
        this.roundPrecision = roundPrecision;
    }

    public String getRoundingType() {
        return roundingType;
    }

    public void setRoundingType(String roundingType) {
        this.roundingType = roundingType;
    }

    public List<MeasurementSystemFormulaVariablesResponse> getVariables() {
        return variables;
    }

    public void setVariables(List<MeasurementSystemFormulaVariablesResponse> variables) {
        this.variables = variables;
    }

    public int getFormulaMasterId() {
        return formulaMasterId;
    }

    public void setFormulaMasterId(int formulaMasterId) {
        this.formulaMasterId = formulaMasterId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}