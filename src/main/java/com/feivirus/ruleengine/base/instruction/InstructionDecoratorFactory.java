package com.feivirus.ruleengine.base.instruction;

import com.feivirus.ruleengine.base.instruction.impl.DefaultInstructionDecorator;
import com.feivirus.ruleengine.base.instruction.impl.RiskInstructionDecorator;

public class InstructionDecoratorFactory {

	public static InstructionDecorator createInstructionDecorator(RelationalInstruction relationalInstruction, OperandExtend operandExtend) {
		if(operandExtend == null || operandExtend.getType() == null){
			return new DefaultInstructionDecorator(relationalInstruction);
		}else if(operandExtend.getType() == 1){
			return new RiskInstructionDecorator(relationalInstruction, operandExtend);
		}
		return new DefaultInstructionDecorator(relationalInstruction);
	}
}
