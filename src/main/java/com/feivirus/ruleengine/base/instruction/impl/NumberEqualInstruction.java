package com.feivirus.ruleengine.base.instruction.impl;

import com.feivirus.ruleengine.base.instruction.Operand;
import com.feivirus.ruleengine.base.instruction.RelationalInstruction;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

/**
 * 
 * @author feivirus
 *
 * @param <P>
 * @param <T>
 */
public class NumberEqualInstruction<P, T extends Number> implements RelationalInstruction<P, T>{

	@Override
	public boolean match(P sourceOperand, T targetValue, OperatorEnum operatorEnum) {
		if (sourceOperand instanceof Number && 
			 targetValue instanceof Number &&
			 operatorEnum == OperatorEnum.EQ) {
			 	return sourceOperand.equals(targetValue);
		}
		return false;
	}	
}
