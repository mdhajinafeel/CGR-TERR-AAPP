package com.cgr.codrinterraerp.db.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(
        tableName = "measurement_system_formula_variables",
        indices = {
                @Index(name = "idx_var_name_variable", value = {"varName"}),
                @Index(name = "idx_mid_variable", value = {"measurementSystemId"}),
                @Index(name = "idx_master_id_variable", value = {"formulaMasterId"}),
                @Index(name = "idx_sort_order_variable", value = {"sortOrder"})
        }
)
public class MeasurementSystemFormulaVariables implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int measurementSystemId;
    private int formulaMasterId;
    private String varName;
    private String displayName;
    private String unit;
    private int sortOrder;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMeasurementSystemId() {
        return measurementSystemId;
    }

    public void setMeasurementSystemId(int measurementSystemId) {
        this.measurementSystemId = measurementSystemId;
    }

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