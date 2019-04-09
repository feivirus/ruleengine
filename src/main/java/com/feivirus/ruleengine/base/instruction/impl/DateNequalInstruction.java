package com.feivirus.ruleengine.base.instruction.impl;

import java.util.Date;

import com.feivirus.ruleengine.base.instruction.RelationalInstruction;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

/**
 * 
 * @author feivirus
 *
 */
public class DateNequalInstruction implements RelationalInstruction<Date, Date>{

	@Override
	public boolean match(Date sourceOperand, Date targetValue, OperatorEnum operatorEnum) {
		if(operatorEnum.equals(OperatorEnum.NEQ)) {
			return !sourceOperand.equals(targetValue);
		}
		return false;
	}
}
