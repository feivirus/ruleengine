package com.feivirus.ruleengine.base.instruction.impl;

import java.util.Date;

import com.feivirus.ruleengine.base.instruction.RelationalInstruction;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

/**
 * 
 * @author feivirus
 *
 */
public class DateLessThanInstruction implements RelationalInstruction<Date, Date>{
	@Override
	public boolean match(Date sourceOperand, Date targetValue, OperatorEnum operatorEnum) {
		if (operatorEnum == OperatorEnum.LT) {
			return targetValue.before(sourceOperand);
		}
		return false;
	}
}
