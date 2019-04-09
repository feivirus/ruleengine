package com.feivirus.ruleengine.enums.baseInfoEnums;

/**
 * @Description:规则操作动作
 * @Date Created in 11:18 2018/9/5.
 * @Author mofeng
 */
public enum ActionEnum {
    ADD("添加", 1),
    UPDATE("修改", 2),
    AUDIT_PASS("审核通过", 3),
    AUDIT_FAIL("审核不通过", 4),
    UPDATE_TIME("修改有效期", 5),
    EXPIRED("失效", 6),
    DELETE("删除", 7),
    INIT("初始化", 8);

    //操作描述
    private final String desc;
    //操作code
    private final Integer code;

    ActionEnum(String desc, Integer code) {
        this.desc = desc;
        this.code = code;
    }

    public static ActionEnum getEnumByCode(Integer code) {
        if (null == code) {
            return null;
        }
        for (ActionEnum operatorEnum : ActionEnum.values()) {
            if (operatorEnum.getCode().equals(code)) {
                return operatorEnum;
            }
        }
        return null;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getCode() {
        return code;
    }
}
