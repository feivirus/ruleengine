package com.feivirus.ruleengine.base.instruction.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.feivirus.common.rule.util.NumberUtil;
import com.feivirus.ruleengine.base.instruction.Operand;
import com.feivirus.ruleengine.base.instruction.RelationalInstruction;
import com.feivirus.ruleengine.base.instruction.operand.ValueRangeOperand;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

public class ValueRangeNumberInInstruction <P extends ValueRangeOperand<?> , T extends Number> implements RelationalInstruction<P, T>{

	@Override
	public boolean match(P sourceOperand, T targetValue, OperatorEnum operatorEnum) {
		if (sourceOperand == null || operatorEnum == null) {
			return false;
		}
		if (operatorEnum == OperatorEnum.IN) {
			return matchInInstruction(sourceOperand, targetValue);
		} else if (operatorEnum == OperatorEnum.NOT_IN) {
			return !matchInInstruction(sourceOperand, targetValue);
		}
		return false;
	}
	
	private boolean matchInInstruction(P sourceOperand, T targetValue) {
		List<Object> operandList = (List<Object>) sourceOperand.getOperandList();
		if (CollectionUtils.isEmpty(operandList)) {
			return false;
		}
		
		for(int i = 0; i < operandList.size(); i++) {
			Operand<T> operandItem = (Operand<T>)operandList.get(i);
			
			if (operandItem.value() instanceof Number && 
				targetValue instanceof Number) {
				 if (NumberUtil.toDouble(operandItem.value()) == 
					 NumberUtil.toDouble(targetValue)) {
					return true;
				}
			}
		}
		return false;
	}
}
