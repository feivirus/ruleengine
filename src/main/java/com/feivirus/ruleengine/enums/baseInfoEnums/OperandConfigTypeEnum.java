package com.feivirus.ruleengine.enums.baseInfoEnums;

/**
 * @Description:是否自定义
 * @Date Created in 15:30 2018/9/4.
 * @Author mofeng
 */
public enum OperandConfigTypeEnum {

    CUSTOM("custom", 1), // 用户自定义输入
    DEFINED("defined", 2);// 后端返回可选范围;

    private final String code;
    private final Integer value;

    OperandConfigTypeEnum(String code, Integer value) {
        this.code = code;
        this.value = value;
    }

    public static OperandConfigTypeEnum getByValue(Integer value) {
        if (null == value) {
            return null;
        } else {
            for (OperandConfigTypeEnum configTypeEnum : OperandConfigTypeEnum.values()) {
                if (configTypeEnum.getValue() == value) {
                    return configTypeEnum;
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
