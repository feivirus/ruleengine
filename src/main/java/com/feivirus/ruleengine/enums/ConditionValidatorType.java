package com.feivirus.ruleengine.enums;


/**
 * @author feivirus
 *         <p>
 *         枚举后面跟着具体检测类
 */
public enum ConditionValidatorType {

    //字符串校验(StringConditionChecker)
    STRING_CHECKER(0),

    //整数校验(IntegerConditionChecker)
    INTEGER_CHECKER(1),

    // 地区校验(AreaConditionChecker)
    AREA_CODE_CHECKER(2),

    //日期格式校验(DateValueRangeConditionChecker)
    DATE_VALUE_RANGE_CHECKER(3),

    // 客户评分(取值是)特殊逻辑校验器(PersonGradeNumberValueConditionChecker)
    PERSONGRADE_NUMBER_VALUE_CHECKER(4),

    //Double形式的区间检验(DoubleValueRangeConditionChecker)
    DOUBLE_VALUE_RANGE_CHECKER(5),

    // 客户评分(范围是)特殊逻辑校验器(PersonGradeNumberRangeConditionChecker)
    PERSONGRADE_NUMBER_RANGE_CHECKER(6),

    //Integer形式的区间校验(IntegerValueRangeConditionChecker)
    INTEGER_VALUE_RANGE_CHECKER(8),

    //Boolean形式的校验(BooleanConditionChecker)
    BOOLEAN_CHECKER(9),

    //Double形式的检验(DoubleConditionChecker)
    DOUBLE_CHECKER(13);


    private int value;

    ConditionValidatorType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ConditionValidatorType getConditionCheckerEnum(Integer param) {
        if (param != null) {
            for (ConditionValidatorType checkerEnum : ConditionValidatorType.values()) {
                if (checkerEnum.getValue() == param.intValue()) {
                    return checkerEnum;
                }
            }
        }
        return null;
    }


}
