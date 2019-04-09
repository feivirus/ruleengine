package com.feivirus.ruleengine.businessChecker;

import org.junit.Assert;
import org.junit.Test;

import com.feivirus.ruleengine.base.RuleValidator;
import com.feivirus.ruleengine.commission.CommissionRule;
import com.feivirus.ruleengine.enums.ConditionValidatorType;
import com.feivirus.ruleengine.enums.RelationEnum;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;
import com.feivirus.ruleengine.rule.CommissionRuleTest;
import com.feivirus.ruleengine.rule.dto.ValueRange;

/**
 * @author mofeng
 * @description:
 * @date 2019/2/13
 */
public class PersonGradeNumberRangeConditionCheckerTest extends CommissionRuleTest {

    /**
     * 该维度的判断 除了相离关系其他的都是相交关系
     */
    @Test
    public void intersect() {
        // 相等得到数据
        ValueRange<Double, Double> target = new ValueRange<Double, Double>(1.0, 2.0, null);
        ValueRange<Double, Double> src = new ValueRange<Double, Double>(1.0, 2.0, null);
        setPersonGradeNumberRangeParam(target, src, RelationEnum.INTERSECT);
    }


    @Test
    public void separate() {
        ValueRange<Double, Double> target = new ValueRange<Double, Double>(1.0, 2.0, null);
        ValueRange<Double, Double> src = new ValueRange<Double, Double>(3.0, 5.0, null);
        setPersonGradeNumberRangeParam(target, src, RelationEnum.SEPARATE);
    }


    private void setPersonGradeNumberRangeParam(ValueRange<Double, Double> target, ValueRange<Double, Double> src, RelationEnum relationEnum) {
        String termCode = "carKindTypeList";
        CommissionRule ruleSrc = buildCommissionRule(src, OperatorEnum.IN, termCode, ConditionValidatorType.PERSONGRADE_NUMBER_RANGE_CHECKER);
        CommissionRule ruleTarget = buildCommissionRule(target, OperatorEnum.IN, termCode, ConditionValidatorType.PERSONGRADE_NUMBER_RANGE_CHECKER);
        RelationEnum result = new RuleValidator().validateConflict(ruleSrc, ruleTarget);
        System.out.println(result);
        Assert.assertTrue(result.equals(relationEnum));
    }

}
