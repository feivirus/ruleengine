package com.feivirus.ruleengine.base.instruction;

import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

public interface InstructionDecorator {

    public boolean match(Object sourceOperand, Object targetValue, OperatorEnum operatorEnum);

}
