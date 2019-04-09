package com.feivirus.ruleengine.rule;

import java.util.HashMap;
import java.util.Map;

import com.feivirus.ruleengine.commission.CommissionRule;
import com.feivirus.ruleengine.commission.RuleCondition;
import com.feivirus.ruleengine.enums.ConditionValidatorType;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

/**
 * @author feivirus
 */
public class CommissionRuleTest {


    public CommissionRule buildCommissionRule(Object operand, OperatorEnum operatorEnum,
                                              String termCode, ConditionValidatorType validatorType) {
        CommissionRule commissionRule = new CommissionRule();
        Map<String, RuleCondition> receivableConditionMap = new HashMap<>();

        RuleCondition ruleCondition = new RuleCondition();
        ruleCondition.setOperation(operatorEnum.getCode());
        ruleCondition.setTermCode(termCode);
        ruleCondition.setOperand(operand);
        ruleCondition.setCheckerType(validatorType.getValue());

        receivableConditionMap.put(termCode, ruleCondition);
        commissionRule.setRuleConditionMap(receivableConditionMap);
        return commissionRule;
    }

}
