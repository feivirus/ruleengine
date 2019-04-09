package com.feivirus.ruleengine.base.instruction;

import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

/**
 * 
 * @author feivirus
 *
 * @param <T>
 */
public interface ArithmeticInstruction<T> {
	//算数运算符，比如加减乘除
	T eval(T sourceOperand, T targetOperand, OperatorEnum operatorEnum);	
}
