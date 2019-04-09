package com.feivirus.ruleengine.base.checker.businesschecker;

import com.feivirus.ruleengine.base.ConditionChecker;
import com.feivirus.ruleengine.enums.RelationEnum;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;
import com.feivirus.ruleengine.rule.dto.ValueRange;

/**
 * 客户评分特殊逻辑校验器
 * 备注：客户评分的规则维度是：（相交INTERSECT，子集SUBSET，父集CONTAIN，相等EQUAL）都是冲突不可插入
 *
 * @author mofeng
 */
public class PersonGradeNumberRangeConditionChecker extends ValueRange<Double, Double> implements ConditionChecker<ValueRange<Double, Double>> {


    public PersonGradeNumberRangeConditionChecker(Double s, Double e, OperatorEnum operation) {
        super(s, e, operation);
    }

    @Override
    public RelationEnum compare(ValueRange<Double, Double> target) {
        ValueRange<Double, Double> srcRange = new ValueRange<>(start, end, operation);
        RelationEnum relationEnum = compare(srcRange, target);
        if (relationEnum == RelationEnum.EQUAL ||
                relationEnum == RelationEnum.CONTAIN ||
                relationEnum == RelationEnum.INTERSECT ||
                relationEnum == RelationEnum.SUBSET||
                relationEnum == RelationEnum.UNKNOWN) {
            return RelationEnum.INTERSECT;
        } else {
            return RelationEnum.SEPARATE;
        }
    }

}
