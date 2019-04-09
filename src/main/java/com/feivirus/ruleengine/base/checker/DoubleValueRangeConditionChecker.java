package com.feivirus.ruleengine.base.checker;

import com.feivirus.ruleengine.base.ConditionChecker;
import com.feivirus.ruleengine.base.IRuleCondition;
import com.feivirus.ruleengine.enums.RelationEnum;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;
import com.feivirus.ruleengine.rule.dto.ValueRange;

/**
 * @author feivirus
 */
public class DoubleValueRangeConditionChecker extends ValueRange<Double, Double> implements ConditionChecker<ValueRange<Double, Double>> {


    public DoubleValueRangeConditionChecker(Double s, Double e, OperatorEnum operation) {
        super(s, e, operation);
    }

    @Override
    public RelationEnum compare(ValueRange<Double, Double> target) {
        ValueRange<Double, Double> srcRange = new ValueRange<>(start, end, operation);
        RelationEnum relationEnum = compare(srcRange, target);
        return relationEnum;
    }

}
