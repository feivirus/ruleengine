package com.feivirus.ruleengine.base.checker;

import com.feivirus.ruleengine.enums.RelationEnum;

/**
 * @author feivirus
 */
public class NumberConditionChecker {
    public static <T extends Number> RelationEnum compare(T src, T target) {
        //前端可能传-1代表没有值，和null一样
        if (src != null && target != null && src.doubleValue() != -1 && target.doubleValue() != -1 && src.doubleValue() != target.doubleValue()) {
            return RelationEnum.SEPARATE;
        }
        if (src == null && target == null) {
            return RelationEnum.UNKNOWN;
        }
        if (src == null && target != null) {
            return RelationEnum.CONTAIN;
        }
        if (src != null && target == null) {
            return RelationEnum.SUBSET;
        }
        double srcValue = src.doubleValue();
        double targetValue = target.doubleValue();

        if (srcValue == -1 && targetValue == -1) {
            return RelationEnum.UNKNOWN;
        }
        if (srcValue == -1 && targetValue != -1) {
            return RelationEnum.CONTAIN;
        }
        if (srcValue != -1 && targetValue == -1) {
            return RelationEnum.SUBSET;
        }
        if (srcValue == targetValue) {
            return RelationEnum.EQUAL;
        }
        return RelationEnum.UNKNOWN;
    }
}
