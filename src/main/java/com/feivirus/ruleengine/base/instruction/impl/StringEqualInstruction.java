package com.feivirus.ruleengine.base.instruction.impl;

import com.feivirus.ruleengine.base.instruction.RelationalInstruction;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

public class StringEqualInstruction implements RelationalInstruction<String, String>{

	@Override
	public boolean match(String sourceOperand, String targetValue, OperatorEnum operatorEnum) {
		if(operatorEnum.equals(OperatorEnum.EQ)) {
			return sourceOperand.equals(targetValue);
		}
		return false;
	}

}
