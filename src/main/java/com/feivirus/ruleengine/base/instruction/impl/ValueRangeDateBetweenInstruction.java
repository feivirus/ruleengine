package com.feivirus.ruleengine.base.instruction.impl;

import java.util.Date;
import java.util.List;

import com.feivirus.ruleengine.base.instruction.Operand;
import com.feivirus.ruleengine.base.instruction.RelationalInstruction;
import com.feivirus.ruleengine.base.instruction.operand.ValueRangeOperand;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

/**
 * 
 * @author feivirus
 *
 * @param <P>
 * @param <T>
 */
public class ValueRangeDateBetweenInstruction<P extends ValueRangeOperand<?> , T extends Date> implements RelationalInstruction<P, T>{

	@Override
	public boolean match(P sourceOperand, T targetValue, OperatorEnum operatorEnum) {
		if (sourceOperand == null || operatorEnum == null) {
			return false;
		}
		
		if (operatorEnum == OperatorEnum.NOT_EQ_BETWEEN) {				
			List<Object> operandList = (List<Object>) sourceOperand.getOperandList();
			Operand<T> firstOperand = (Operand<T>)operandList.get(0);
			Operand<T> secondOperand = (Operand<T>)operandList.get(1);
			
			if (firstOperand.value() instanceof Date && 
				targetValue instanceof Date &&
				secondOperand.value() instanceof Date) {
				boolean lowerLimit = new DateGreaterThanInstruction().match(firstOperand.value(), targetValue, OperatorEnum.GT);
				boolean upperLimit =new DateLessThanInstruction().match(secondOperand.value(), targetValue, OperatorEnum.LT);
				
				if (upperLimit && lowerLimit) {
					return true;
				}
			}
		} 
		return false;
	}
}
