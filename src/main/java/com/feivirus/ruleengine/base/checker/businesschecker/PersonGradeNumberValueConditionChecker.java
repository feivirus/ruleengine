package com.feivirus.ruleengine.base.checker.businesschecker;

import com.feivirus.ruleengine.base.ConditionChecker;
import com.feivirus.ruleengine.base.checker.NumberConditionChecker;
import com.feivirus.ruleengine.enums.RelationEnum;

/**
 * 客户评分特殊逻辑校验器
 * 备注：客户评分的规则维度是：（相交INTERSECT，子集SUBSET，父集CONTAIN，相等EQUAL）都是冲突不可插入
 *
 * @author mofeng
 */
public class PersonGradeNumberValueConditionChecker implements ConditionChecker<Double> {

    private Double value;

    @Override
    public RelationEnum compare(Double target) {
        RelationEnum relationEnum = NumberConditionChecker.compare(value, target);
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}
