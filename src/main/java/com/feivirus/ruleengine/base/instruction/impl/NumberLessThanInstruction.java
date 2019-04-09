package com.feivirus.ruleengine.base.instruction.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feivirus.ruleengine.base.instruction.RelationalInstruction;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;
import com.ideacome.common.rule.util.NumberUtil;

/**
 * 
 * @author feivirus
 *
 * @param <T>
 * @param <P>
 */
public class NumberLessThanInstruction<T extends Number, P extends Number> implements RelationalInstruction<P, T>{

	@Override
	public boolean match(P sourceOperand, T targetValue, OperatorEnum operatorEnum) {
		if (NumberUtil.isNumber(sourceOperand, targetValue) && 
			 operatorEnum == OperatorEnum.LT) {
				double sourceDoubleValue = NumberUtil.toDouble(sourceOperand);
				double targetDoubleValue = NumberUtil.toDouble(targetValue);
				return  targetDoubleValue < sourceDoubleValue;
			}
		return false;
	}	
}
