package com.cgr.codrinterraerp.db.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(
        tableName = "measurement_system_formulas",
        indices = {
                @Index(name = "idx_mid_formula", value = {"measurementSystemId"}),
                @Index(name = "idx_formula", value = {"formula"}),
                @Index(name = "idx_round_precision_formula", value = {"roundPrecision"}),
                @Index(name = "idx_rounding_type_formula", value = {"roundingType"})
        }
)
public class MeasurementSystemFormulas implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int measurementSystemId;
    private String formula;
    private int roundPrecision;
    private String roundingType;
    private List<MeasurementSystemFormulaVariables> variables;

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

    public List<MeasurementSystemFormulaVariables> getVariables() {
        return variables;
    }

    public void setVariables(List<MeasurementSystemFormulaVariables> variables) {
        this.variables = variables;
    }
}