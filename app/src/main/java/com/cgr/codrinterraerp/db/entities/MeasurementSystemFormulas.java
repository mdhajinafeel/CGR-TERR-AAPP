package com.cgr.codrinterraerp.db.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(
        tableName = "measurement_system_formulas",
        indices = {
                @Index(name = "idx_master_id_formula", value = {"formulaMasterId"}),
                @Index(name = "idx_mid_formula", value = {"measurementSystemId"}),
                @Index(name = "idx_formula", value = {"formula"}),
                @Index(name = "idx_round_precision_formula", value = {"roundPrecision"}),
                @Index(name = "idx_rounding_type_formula", value = {"roundingType"}),
                @Index(name = "idx_context_formula", value = {"context"})
        }
)
public class MeasurementSystemFormulas implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int formulaMasterId;
    private int measurementSystemId;
    private String formula;
    private int roundPrecision;
    private String roundingType;
    private String context;

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