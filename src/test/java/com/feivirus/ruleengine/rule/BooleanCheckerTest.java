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
 * @description:
 * @date 2019/2/13
 */
public class BooleanCheckerTest extends CommissionRuleTest {

    @Test
    public void equal() {
        Boolean target = true;
        Boolean src = true;
        setBooleanParam(target, src, RelationEnum.EQUAL);
    }

    @Test
    public void separate() {
        Boolean target = true;
        Boolean src = false;
        setBooleanParam(target, src, RelationEnum.SEPARATE);
    }

    private void setBooleanParam(Boolean target, Boolean src, RelationEnum relationEnum) {
        String termCode = "carKindTypeList";
        CommissionRule ruleSrc = buildCommissionRule(src, OperatorEnum.IN, termCode, ConditionValidatorType.BOOLEAN_CHECKER);
        CommissionRule ruleTarget = buildCommissionRule(target, OperatorEnum.IN, termCode, ConditionValidatorType.BOOLEAN_CHECKER);
        RelationEnum result = new RuleValidator().validateConflict(ruleSrc, ruleTarget);
        Assert.assertTrue(result.equals(relationEnum));
    }
}
