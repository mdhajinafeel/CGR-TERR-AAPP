package com.cgr.codrinterraerp.utils;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public class FormulaEngine {

    public static double evaluate(String formula, Map<String, Double> variables) {

        try {
            Expression expression = new ExpressionBuilder(formula)
                    .variables(variables.keySet())
                    .build();

            for (Map.Entry<String, Double> entry : variables.entrySet()) {
                expression.setVariable(entry.getKey(), entry.getValue());
            }

            return expression.evaluate();

        } catch (Exception e) {
            throw new RuntimeException("Formula error: " + e.getMessage());
        }
    }

    public static double applyRounding(double value, int precision, String type) {
        BigDecimal bd = new BigDecimal(value);

        switch (type) {
            case "TRUNCATE":
                bd = bd.setScale(precision, RoundingMode.DOWN);
                break;
            case "ROUND":
                bd = bd.setScale(precision, RoundingMode.HALF_UP);
                break;
            case "CEIL":
                bd = bd.setScale(precision, RoundingMode.CEILING);
                break;
            case "FLOOR":
                bd = bd.setScale(precision, RoundingMode.FLOOR);
                break;
        }

        return bd.doubleValue();
    }
}