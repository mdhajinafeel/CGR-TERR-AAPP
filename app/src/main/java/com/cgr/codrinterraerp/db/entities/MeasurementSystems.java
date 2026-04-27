package com.cgr.codrinterraerp.db.entities;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "measurement_systems",
        indices = {
                @Index(name = "idx_id_measurement", value = {"id"}),
                @Index(name = "idx_measurement_name", value = {"measurementName"}),
                @Index(name = "idx_product_type_id_measurement", value = {"productTypeId"})
        }
)
public class MeasurementSystems implements Serializable {

    @PrimaryKey
    private int id;
    private String measurementName;
    private int productTypeId;
    private List<MeasurementSystemFormulas> formulas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public void setMeasurementName(String measurementName) {
        this.measurementName = measurementName;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public List<MeasurementSystemFormulas> getFormulas() {
        return formulas;
    }

    public void setFormulas(List<MeasurementSystemFormulas> formulas) {
        this.formulas = formulas;
    }
}