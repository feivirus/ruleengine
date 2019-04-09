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
 * @description: String类型校验器
 * @date 2019/2/13
 */
public class StringCheckerTest extends CommissionRuleTest {

    @Test
    public void equals() {
        String src = "A0101,A0102,A0103";
        String target = "A0101,A0102,A0103";
        setStringParam(target, src, RelationEnum.EQUAL);
    }

    @Test
    public void intersect() {
        String src = "A0101,A0102,A0103";
        String target = "A0102,A0103,A0104";
        setStringParam(target, src, RelationEnum.INTERSECT);
    }

    @Test
    public void separate() {
        String src = "A0101,A0102";
        String target = "A0103,A0104";
        setStringParam(target, src, RelationEnum.SEPARATE);
    }

    @Test
    public void subset() {
        String src = "A0102,A0103";
        String target = "A0101,A0102,A0103,A0104";
        setStringParam(target, src, RelationEnum.SUBSET);
    }

    @Test
    public void contain() {
        String src = "A0101,A0102,A0103,A0104";
        String target = "A0102,A0103";
        setStringParam(target, src, RelationEnum.CONTAIN);
    }

    private void setStringParam(String target, String src, RelationEnum relationEnum) {
        String termCode = "carKindTypeList";
        CommissionRule ruleSrc = buildCommissionRule(src, OperatorEnum.IN, termCode, ConditionValidatorType.STRING_CHECKER);
        CommissionRule ruleTarget = buildCommissionRule(target, OperatorEnum.IN, termCode, ConditionValidatorType.STRING_CHECKER);
        RelationEnum result = new RuleValidator().validateConflict(ruleSrc, ruleTarget);
        Assert.assertTrue(result.equals(relationEnum));
    }

}
