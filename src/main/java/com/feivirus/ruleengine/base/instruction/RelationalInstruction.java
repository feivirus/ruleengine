package com.feivirus.ruleengine.base.instruction;

import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

/**
 * 
 * @author feivirus
 *
 * @param <T>
 *  关系运算符，比如==,!=, <,>, in
 */
public interface RelationalInstruction<P, T> {
	//匹配
	//TODO
	//去掉OperatorEnum参数,这个参数破坏了封装性，会使一个指令内部适配不同的操作符
	boolean match(P sourceOperand, T targetValue, OperatorEnum operatorEnum);	
}
