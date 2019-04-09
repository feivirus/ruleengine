package com.feivirus.ruleengine.base.instruction.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.feivirus.ruleengine.base.instruction.Operand;
import com.feivirus.ruleengine.base.instruction.RelationalInstruction;
import com.feivirus.ruleengine.base.instruction.operand.ValueRangeOperand;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;
import com.ideacome.common.rule.util.NumberUtil;

public class StringInInstruction<P extends ValueRangeOperand<?>> implements RelationalInstruction<P, String>{
	@Override
	public boolean match(P sourceOperand, String targetValue, OperatorEnum operatorEnum) {
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
	
	
	private boolean matchInInstruction(P sourceOperand, String targetValue) {
		List<Object> operandList = (List<Object>) sourceOperand.getOperandList();
		if (CollectionUtils.isEmpty(operandList)) {
			return false;
		}
		
		for(int i = 0; i < operandList.size(); i++) {
			Operand<String> operandItem = (Operand<String>)operandList.get(i);
			
			if (operandItem.value() instanceof String) {
				 if (operandItem.value().equals(targetValue)) {
					return true;
				}
			}
		}
		return false;
	}
}
