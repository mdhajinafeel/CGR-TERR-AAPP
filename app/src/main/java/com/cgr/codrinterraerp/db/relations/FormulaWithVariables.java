package com.cgr.codrinterraerp.db.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.cgr.codrinterraerp.db.entities.MeasurementSystemFormulaVariables;
import com.cgr.codrinterraerp.db.entities.MeasurementSystemFormulas;

import java.util.List;

public class FormulaWithVariables {

    @Embedded
    public MeasurementSystemFormulas formula;

    @Relation(
            parentColumn = "formulaMasterId",
            entityColumn = "formulaMasterId"
    )
    public List<MeasurementSystemFormulaVariables> variables;
}