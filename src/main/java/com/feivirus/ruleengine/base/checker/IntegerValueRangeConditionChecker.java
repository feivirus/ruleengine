package com.feivirus.ruleengine.base.checker;

import com.feivirus.ruleengine.base.ConditionChecker;
import com.feivirus.ruleengine.base.IRuleCondition;
import com.feivirus.ruleengine.enums.RelationEnum;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;
import com.feivirus.ruleengine.rule.dto.ValueRange;

/**
 * @author feivirus
 */
public class IntegerValueRangeConditionChecker extends ValueRange<Integer, Integer> implements ConditionChecker<ValueRange<Integer, Integer>> {


    public IntegerValueRangeConditionChecker(Integer s, Integer e, OperatorEnum operation) {
        super(s, e, operation);
    }

    @Override
    public RelationEnum compare(ValueRange<Integer, Integer> target) {
        ValueRange<Integer, Integer> src = new ValueRange<Integer, Integer>(start, end, operation);
        RelationEnum relationEnum = compare(src, target);
        return relationEnum;
    }

}
