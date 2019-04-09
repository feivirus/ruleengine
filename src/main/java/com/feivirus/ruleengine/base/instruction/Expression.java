package com.feivirus.ruleengine.base.instruction;

import java.io.Serializable;

import com.feivirus.ruleengine.enums.RelationEnum;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

/**
 * 
 * @author feivirus
 *
 * @param <T>
 */
public interface Expression<P, T> extends Serializable, RelationalInstruction<P, T>{	
	//操作符
	OperatorEnum operator();
	
	//操作数
	Operand operand();
}
