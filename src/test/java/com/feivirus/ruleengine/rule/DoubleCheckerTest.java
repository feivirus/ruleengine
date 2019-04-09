package com.feivirus.ruleengine.rule;

import org.junit.Assert;
import org.junit.Test;

import com.feivirus.ruleengine.base.RuleValidator;
import com.feivirus.ruleengine.commission.CommissionRule;
import com.feivirus.ruleengine.enums.ConditionValidatorType;
import com.feivirus.ruleengine.enums.RelationEnum;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

/**
 * @author mofeng
 * @description: Double类型校验器
 * @date 2019/2/13
 */
public class DoubleCheckerTest extends CommissionRuleTest {

    @Test
    public void equal(){
        Double src = 1.0;
        Double target = 1.0;
        setDoubleParam(src, target, RelationEnum.EQUAL);
    }

    @Test
    public void subset(){
        Double src = 1.0;
        Double target = -1.0;
        setDoubleParam(src, target, RelationEnum.SUBSET);
    }

    @Test
    public void contain(){
        Double src = -1.0;
        Double target = 1.0;
        setDoubleParam(src, target, RelationEnum.CONTAIN);
    }


    private void setDoubleParam(Double src, Double target, RelationEnum relationEnum) {
        String termCode = "forceClass";
        CommissionRule ruleSrc = buildCommissionRule(src, OperatorEnum.EQ, termCode, ConditionValidatorType.DOUBLE_CHECKER);
        CommissionRule ruleTarget = buildCommissionRule(target, OperatorEnum.EQ, termCode, ConditionValidatorType.DOUBLE_CHECKER);
        RelationEnum result = new RuleValidator().validateConflict(ruleSrc, ruleTarget);
        Assert.assertTrue(result.equals(relationEnum));
    }
}
