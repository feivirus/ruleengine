package com.feivirus.ruleengine.base.instruction.impl;

import com.feivirus.ruleengine.base.instruction.InstructionDecorator;
import com.feivirus.ruleengine.base.instruction.OperandExtend;
import com.feivirus.ruleengine.base.instruction.RelationalInstruction;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

/**
 * 险种特殊处理场景
 * 考虑不投保险种场景
 */
public class RiskInstructionDecorator implements InstructionDecorator {

    private RelationalInstruction instruction;

    private OperandExtend operandExtend;

    public RiskInstructionDecorator(RelationalInstruction instruction, OperandExtend operandExtend) {
        this.instruction = instruction;
        this.operandExtend = operandExtend;
    }

    @Override
    public boolean match(Object sourceOperand, Object targetValue, OperatorEnum operatorEnum) {
        //未购买险种
        Integer isBuy = 0;
        if((instruction instanceof StringInInstruction
                || instruction instanceof StringNequalInstruction
                || instruction instanceof StringEqualInstruction)
            && isBuy.equals(operandExtend.getObject())){
            return instruction.match(sourceOperand, "non", operatorEnum);
        }
        return instruction.match(sourceOperand,targetValue,operatorEnum);
    }
}
