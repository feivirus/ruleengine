package com.feivirus.ruleengine.rule;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.feivirus.ruleengine.base.RuleValidator;
import com.feivirus.ruleengine.commission.CommissionRule;
import com.feivirus.ruleengine.enums.ConditionValidatorType;
import com.feivirus.ruleengine.enums.RelationEnum;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

/**
 * @author mofeng
 * @description: 地区冲突校验
 * @date 2019/2/13
 */
public class AreaCodeCheckerTest extends CommissionRuleTest {

    @Test
    public void equal() {
        List<String> src = new ArrayList<>();
        src.add("110101");
        src.add("110100");
        List<String> target = new ArrayList<>();
        target.add("110100");
        target.add("110101");
        setAreaCodeParam(target, src, RelationEnum.EQUAL);
    }

    @Test
    public void intersect() {
        List<String> src = new ArrayList<>();
        src.add("110101");
        src.add("110102");
        src.add("110103");
        List<String> target = new ArrayList<>();
        target.add("110102");
        target.add("110103");
        target.add("110104");
        setAreaCodeParam(target, src, RelationEnum.INTERSECT);
    }

    @Test
    public void separate() {
        List<String> src = new ArrayList<>();
        src.add("110101");
        src.add("110102");
        src.add("110103");
        List<String> target = new ArrayList<>();
        target.add("110104");
        target.add("110105");
        setAreaCodeParam(target, src, RelationEnum.SEPARATE);
    }

    @Test
    public void contain1() {
        List<String> src = new ArrayList<>();
        src.add("110101");
        src.add("110102");
        src.add("110103");
        src.add("110104");
        src.add("110105");
        List<String> target = new ArrayList<>();
        target.add("110104");
        target.add("110105");
        setAreaCodeParam(target, src, RelationEnum.CONTAIN);
    }

    @Test
    public void contain2() {
        List<String> target = new ArrayList<>();
        target.add("110100");
        List<String> src = new ArrayList<>();
        src.add("110105");
        setAreaCodeParam(target, src, RelationEnum.CONTAIN);
    }

    @Test
    public void subset1(){
        List<String> target = new ArrayList<>();
        target.add("110101");
        target.add("110102");
        target.add("110103");
        target.add("110104");
        target.add("110106");
        List<String> src = new ArrayList<>();
        src.add("110104");
        src.add("110106");
        setAreaCodeParam(target, src, RelationEnum.SUBSET);
    }

    @Test
    public void subset2(){
        List<String> target = new ArrayList<>();
        target.add("110105");
        List<String> src = new ArrayList<>();
        src.add("110100");
        setAreaCodeParam(target, src, RelationEnum.SUBSET);
    }

    private void setAreaCodeParam(List<String> target, List<String> src, RelationEnum relationEnum) {
        String termCode = "forceClass";
        CommissionRule ruleSrc = buildCommissionRule(src, OperatorEnum.IN, termCode, ConditionValidatorType.AREA_CODE_CHECKER);
        CommissionRule ruleTarget = buildCommissionRule(target, OperatorEnum.IN, termCode, ConditionValidatorType.AREA_CODE_CHECKER);
        RelationEnum result = new RuleValidator().validateConflict(ruleSrc, ruleTarget);
        Assert.assertTrue(result.equals(relationEnum));
    }

}
