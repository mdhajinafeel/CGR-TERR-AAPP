package com.cgr.codrinterraerp.model.response.masterdata;

import java.io.Serializable;

public class MeasurementSystemFormulaVariablesResponse implements Serializable {

    private int formulaMasterId, sortOrder;
    private String varName, displayName, unit;

    public int getFormulaMasterId() {
        return formulaMasterId;
    }

    public void setFormulaMasterId(int formulaMasterId) {
        this.formulaMasterId = formulaMasterId;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }
}