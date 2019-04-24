package com.feivirus.ruleengine.base.instruction.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.feivirus.common.rule.util.NumberUtil;
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
public class ValueRangeNumberBetweenInstruction<P extends ValueRangeOperand<?> , T extends Number> implements RelationalInstruction<P, T>{

	@Override
	public boolean match(P sourceOperand, T targetValue, OperatorEnum operatorEnum) {
		if (sourceOperand == null || operatorEnum == null) {
			return false;
		}
		
		if (operatorEnum == OperatorEnum.NOT_EQ_BETWEEN || 
			operatorEnum == OperatorEnum.EQ_BETWEEN || 
			operatorEnum == OperatorEnum.LEFT_EQ_BETWEEN || 
			operatorEnum == OperatorEnum.RIGHT_EQ_BETWEEN) {		
			return matchBetweenInstruction(sourceOperand, targetValue, operatorEnum);
		}				
		return false;
	}			
	
	private boolean matchBetweenInstruction(P sourceOperand, T targetValue, OperatorEnum operatorEnum) {
		List<Object> operandList = (List<Object>) sourceOperand.getOperandList();
		Operand<T> firstOperand = (Operand<T>)operandList.get(0);
		Operand<T> secondOperand = (Operand<T>)operandList.get(1);

		if (firstOperand.value() instanceof Number &&
			targetValue instanceof Number && 
			secondOperand.value() instanceof Number) {
			boolean lowerLimit = false;
			boolean upperLimit = false;
			
			if (operatorEnum == OperatorEnum.EQ_BETWEEN) {
				lowerLimit = new NumberGreaterEqualThanInstruction<T, T>().match(firstOperand.value(), targetValue, OperatorEnum.EGT);
				upperLimit = new NumberLessEqualInstruction<T, T>().match(secondOperand.value(), targetValue, OperatorEnum.ELT);
			} else if (operatorEnum == OperatorEnum.NOT_EQ_BETWEEN) {
				lowerLimit = new NumberGreaterThanInstruction<T, T>().match(firstOperand.value(), targetValue, OperatorEnum.GT);
				upperLimit = new NumberLessThanInstruction<T, T>().match(secondOperand.value(), targetValue, OperatorEnum.LT);
            } else if (operatorEnum == OperatorEnum.LEFT_EQ_BETWEEN) {
				lowerLimit = new NumberGreaterEqualThanInstruction<T, T>().match(firstOperand.value(), targetValue, OperatorEnum.EGT);
				upperLimit = new NumberLessThanInstruction<T, T>().match(secondOperand.value(), targetValue, OperatorEnum.LT);
			} else if (operatorEnum == OperatorEnum.RIGHT_EQ_BETWEEN) {
				lowerLimit = new NumberGreaterThanInstruction<T, T>().match(firstOperand.value(), targetValue, OperatorEnum.GT);
				upperLimit = new NumberLessEqualInstruction<T, T>().match(secondOperand.value(), targetValue, OperatorEnum.ELT);
			}
			
			if (lowerLimit && upperLimit) {
				return true;
			}			
		}
		return false;
	}
}
