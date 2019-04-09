package com.feivirus.ruleengine.base.instruction.impl;

import java.util.Date;

import com.feivirus.ruleengine.base.instruction.RelationalInstruction;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

/**
 * 
 * @author feivirus
 *
 */
public class DateGreaterThanInstruction implements RelationalInstruction<Date, Date>{

	@Override
	public boolean match(Date sourceOperand, Date targetValue, OperatorEnum operatorEnum) {
		if (operatorEnum == OperatorEnum.GT) {
			return targetValue.after(sourceOperand);
		}
		return false;
	}

}
