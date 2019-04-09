package com.feivirus.ruleengine.enums.baseInfoEnums;

/**
 * @Description:输入框类型
 * @Date Created in 15:16 2018/9/4.
 * @Author mofeng
 */
public enum InputTypeEnum {

    /*单选*/
    SINGLE_SELECT("single_select", 1),
    /*多选*/
    MULTI_SELECT("multi_select", 2),
    /*数值*/
    RANGE("range", 3),
    /*文本*/
    TEXT("text", 4),
    /*日期*/
    DATE("date", 5);

    private final String code;
    private final Integer value;

    InputTypeEnum(String code, Integer value) {
        this.code = code;
        this.value = value;
    }

    public static InputTypeEnum getByValue(Integer value) {
        if (null == value) {
            return null;
        } else {
            for (InputTypeEnum inputTypeEnum : InputTypeEnum.values()) {
                if (inputTypeEnum.value.equals(value)) {
                    return inputTypeEnum;
                }
            }
            return null;
        }
    }

    public static InputTypeEnum getByCode(String code) {
        if (null == code) {
            return null;
        } else {
            for (InputTypeEnum inputTypeEnum : InputTypeEnum.values()) {
                if (inputTypeEnum.code.equals(code)) {
                    return inputTypeEnum;
                }
            }
            return null;
        }
    }

    public String getCode() {
        return code;
    }

    public Integer getValue() {
        return value;
    }
}
