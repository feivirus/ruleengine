package com.feivirus.ruleengine.base.instruction.impl;

import com.feivirus.common.rule.util.NumberUtil;
import com.feivirus.ruleengine.base.instruction.RelationalInstruction;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

/**
 * 
 * @author feivirus
 *
 * @param <T>
 * @param <P>
 */
public class NumberGreaterEqualThanInstruction<T extends Number, P extends Number> implements RelationalInstruction<P, T>{

	@Override
	public boolean match(P sourceOperand, T targetValue, OperatorEnum operatorEnum) {
		if (NumberUtil.isNumber(sourceOperand, targetValue) && 
			operatorEnum == OperatorEnum.EGT) {
			return NumberUtil.toDouble(targetValue) >= NumberUtil.toDouble(sourceOperand);
		}
		return false;
	}
}
