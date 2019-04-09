package com.feivirus.ruleengine.rule.dto;

import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

/**
 * @author mofeng
 * @description: 操作符
 * @date 2018/12/7
 */
public class RangeOperation {

    protected OperatorEnum operation;

    public RangeOperation(OperatorEnum operation) {
        this.operation = operation;
    }

    public OperatorEnum getOperation() {
        return operation;
    }

    public void setOperation(OperatorEnum operation) {
        this.operation = operation;
    }
}
