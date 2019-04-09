package com.feivirus.ruleengine.base.checker;

import com.feivirus.ruleengine.base.ConditionChecker;
import com.feivirus.ruleengine.enums.RelationEnum;

public class BooleanConditionChecker implements ConditionChecker<Boolean> {
    private Boolean value;

    @Override
    public RelationEnum compare(Boolean target) {
        if (value == null && target == null) {
            return RelationEnum.UNKNOWN;
        }
        if (value == null) {
            return RelationEnum.CONTAIN;
        }
        if (target == null) {
            return RelationEnum.SUBSET;
        }
        if (value.equals(target)) {
            return RelationEnum.EQUAL;
        } else {
            return RelationEnum.SEPARATE;
        }
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
}
