package com.feivirus.ruleengine.base.instruction.impl;

import java.util.Date;

import com.feivirus.ruleengine.base.instruction.RelationalInstruction;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

/**
 * 
 * @author feivirus
 *
 */
public class DateLessEqualThanInstruction implements RelationalInstruction<Date, Date>{
	@Override
	public boolean match(Date sourceOperand, Date targetValue, OperatorEnum operatorEnum) {
		if (sourceOperand == null || targetValue == null) {
			return false;
		}
		if (sourceOperand.equals(targetValue) || sourceOperand.before(targetValue)) {
			return true;
		}		
		return false;
	}
}
