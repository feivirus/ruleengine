package com.feivirus.ruleengine.rule;

import org.junit.Assert;
import org.junit.Test;

import com.feivirus.ruleengine.base.RuleValidator;
import com.feivirus.ruleengine.commission.CommissionRule;
import com.feivirus.ruleengine.enums.ConditionValidatorType;
import com.feivirus.ruleengine.enums.RelationEnum;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;
import com.feivirus.ruleengine.rule.dto.ValueRange;

/**
 * @author mofeng
 * @description: double类型范围检验
 * @date 2019/2/13
 */
public class DoubleValueRangeCheckerTest extends CommissionRuleTest {

    @Test
    public void equal() {
        ValueRange<Double, Double> target = new ValueRange<Double, Double>(1.0, 2.0, null);
        ValueRange<Double, Double> src = new ValueRange<Double, Double>(1.0, 2.0, null);
        setDoubleValueRangeParam(target, src, RelationEnum.EQUAL);
    }

    @Test
    public void subset() {
        ValueRange<Double, Double> target = new ValueRange<Double, Double>(3.0, 4.0, null);
        ValueRange<Double, Double> src = new ValueRange<Double, Double>(1.0, 5.0, null);
        setDoubleValueRangeParam(target, src, RelationEnum.SUBSET);
    }

    @Test
    public void contain() {
        ValueRange<Double, Double> target = new ValueRange<Double, Double>(1.0, 5.0, null);
        ValueRange<Double, Double> src = new ValueRange<Double, Double>(2.0, 4.0, null);
        setDoubleValueRangeParam(target, src, RelationEnum.CONTAIN);
    }

    @Test
    public void separate() {
        ValueRange<Double, Double> target = new ValueRange<Double, Double>(1.0, 5.0, null);
        ValueRange<Double, Double> src = new ValueRange<Double, Double>(6.0, 8.0, null);
        setDoubleValueRangeParam(target, src, RelationEnum.SEPARATE);
    }

    @Test
    public void intersect() {
        ValueRange<Double, Double> target = new ValueRange<Double, Double>(1.0, 5.0, null);
        ValueRange<Double, Double> src = new ValueRange<Double, Double>(4.0, 8.0, null);
        setDoubleValueRangeParam(target, src, RelationEnum.INTERSECT);
    }

    private void setDoubleValueRangeParam(ValueRange<Double, Double> target, ValueRange<Double, Double> src, RelationEnum relationEnum) {
        String termCode = "carKindTypeList";
        CommissionRule ruleSrc = buildCommissionRule(src, OperatorEnum.IN, termCode, ConditionValidatorType.DOUBLE_VALUE_RANGE_CHECKER);
        CommissionRule ruleTarget = buildCommissionRule(target, OperatorEnum.IN, termCode, ConditionValidatorType.DOUBLE_VALUE_RANGE_CHECKER);
        RelationEnum result = new RuleValidator().validateConflict(ruleSrc, ruleTarget);
        Assert.assertTrue(result.equals(relationEnum));
    }

}
