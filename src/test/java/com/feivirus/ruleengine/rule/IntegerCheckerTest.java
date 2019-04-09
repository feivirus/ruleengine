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
 * @description: 整数类型校验器
 * @date 2019/2/13
 */
public class IntegerCheckerTest extends CommissionRuleTest {

    @Test
    public void equal(){
        Integer src = 1;
        Integer target = 1;
        setIntegerParam(src, target, RelationEnum.EQUAL);
    }

    @Test
    public void subset(){
        Integer src = 1;
        Integer target = -1;
        setIntegerParam(src, target, RelationEnum.SUBSET);
    }

    @Test
    public void contain(){
        Integer src = -1;
        Integer target = 1;
        setIntegerParam(src, target, RelationEnum.CONTAIN);
    }


    private void setIntegerParam(Integer src, Integer target, RelationEnum relationEnum) {
        String termCode = "forceClass";
        CommissionRule ruleSrc = buildCommissionRule(src, OperatorEnum.EQ, termCode, ConditionValidatorType.INTEGER_CHECKER);
        CommissionRule ruleTarget = buildCommissionRule(target, OperatorEnum.EQ, termCode, ConditionValidatorType.INTEGER_CHECKER);
        RelationEnum result = new RuleValidator().validateConflict(ruleSrc, ruleTarget);
        Assert.assertTrue(result.equals(relationEnum));
    }
}
