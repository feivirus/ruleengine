package com.feivirus.ruleengine.base.checker;

import com.feivirus.ruleengine.base.ConditionChecker;
import com.feivirus.ruleengine.enums.RelationEnum;

/**
 * @author feivirus
 */
public class IntegerConditionChecker implements ConditionChecker<Integer> {
    private Integer value;

    @Override
    public RelationEnum compare(Integer target) {
        RelationEnum relationEnum = NumberConditionChecker.compare(value, target);
        return relationEnum;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
