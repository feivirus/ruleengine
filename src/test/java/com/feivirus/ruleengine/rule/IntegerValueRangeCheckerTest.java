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
 * @description: integer类型范围检验
 * @date 2019/2/13
 */
public class IntegerValueRangeCheckerTest extends CommissionRuleTest {

    @Test
    public void equal() {
        ValueRange<Integer, Integer> target = new ValueRange<Integer, Integer>(1, 2, null);
        ValueRange<Integer, Integer> src = new ValueRange<Integer, Integer>(1, 2, null);
        setIntegerValueRangeParam(target, src, RelationEnum.EQUAL);
    }

    @Test
    public void subset() {
        ValueRange<Integer, Integer> target = new ValueRange<Integer, Integer>(3, 4, null);
        ValueRange<Integer, Integer> src = new ValueRange<Integer, Integer>(1, 5, null);
        setIntegerValueRangeParam(target, src, RelationEnum.SUBSET);
    }

    @Test
    public void contain() {
        ValueRange<Integer, Integer> target = new ValueRange<Integer, Integer>(1, 5, null);
        ValueRange<Integer, Integer> src = new ValueRange<Integer, Integer>(2, 4, null);
        setIntegerValueRangeParam(target, src, RelationEnum.CONTAIN);
    }

    @Test
    public void separate() {
        ValueRange<Integer, Integer> target = new ValueRange<Integer, Integer>(1, 5, null);
        ValueRange<Integer, Integer> src = new ValueRange<Integer, Integer>(6, 8, null);
        setIntegerValueRangeParam(target, src, RelationEnum.SEPARATE);
    }

    @Test
    public void intersect() {
        ValueRange<Integer, Integer> target = new ValueRange<Integer, Integer>(1, 5, null);
        ValueRange<Integer, Integer> src = new ValueRange<Integer, Integer>(4, 8, null);
        setIntegerValueRangeParam(target, src, RelationEnum.INTERSECT);
    }

    private void setIntegerValueRangeParam(ValueRange<Integer, Integer> target, ValueRange<Integer, Integer> src, RelationEnum relationEnum) {
        String termCode = "carKindTypeList";
        CommissionRule ruleSrc = buildCommissionRule(src, OperatorEnum.IN, termCode, ConditionValidatorType.INTEGER_VALUE_RANGE_CHECKER);
        CommissionRule ruleTarget = buildCommissionRule(target, OperatorEnum.IN, termCode, ConditionValidatorType.INTEGER_VALUE_RANGE_CHECKER);
        RelationEnum result = new RuleValidator().validateConflict(ruleSrc, ruleTarget);
        Assert.assertTrue(result.equals(relationEnum));
    }

}
