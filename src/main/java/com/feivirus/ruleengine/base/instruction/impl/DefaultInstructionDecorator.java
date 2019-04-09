package com.feivirus.ruleengine.base.instruction.impl;

import com.feivirus.ruleengine.base.instruction.InstructionDecorator;
import com.feivirus.ruleengine.base.instruction.RelationalInstruction;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

public class DefaultInstructionDecorator implements InstructionDecorator {

    private RelationalInstruction instruction;

    public DefaultInstructionDecorator(RelationalInstruction instruction) {
        this.instruction = instruction;
    }

    @Override
    public boolean match(Object sourceOperand, Object targetValue, OperatorEnum operatorEnum) {
        return instruction.match(sourceOperand,targetValue,operatorEnum);
    }
}
