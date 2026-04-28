package com.cgr.codrinterraerp.model.response.masterdata;

import java.io.Serializable;
import java.util.List;

public class MeasurementSystemsResponse implements Serializable {

    private int id, productTypeId;
    private String measurementName;
    private List<MeasurementSystemFormulasResponse> formulas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public void setMeasurementName(String measurementName) {
        this.measurementName = measurementName;
    }

    public List<MeasurementSystemFormulasResponse> getFormulas() {
        return formulas;
    }

    public void setFormulas(List<MeasurementSystemFormulasResponse> formulas) {
        this.formulas = formulas;
    }
}