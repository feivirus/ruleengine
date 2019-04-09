package com.feivirus.ruleengine.base.checker;

import com.feivirus.ruleengine.base.ConditionChecker;
import com.feivirus.ruleengine.enums.RelationEnum;

/**
 * @author mofeng
 */
public class DoubleConditionChecker implements ConditionChecker<Double> {
    private Double value;

    @Override
    public RelationEnum compare(Double target) {
        RelationEnum relationEnum = NumberConditionChecker.compare(value, target);
        return relationEnum;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
