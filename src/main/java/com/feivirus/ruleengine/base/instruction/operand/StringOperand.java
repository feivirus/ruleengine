package com.feivirus.ruleengine.base.instruction.operand;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Pattern;

import com.feivirus.ruleengine.base.instruction.Operand;
import com.feivirus.ruleengine.base.instruction.Operation;
import com.feivirus.ruleengine.enums.instruction.OperandEnum;

/**
 * 
 * @author feivirus
 *
 */
public class StringOperand implements Operand<String>, Operation{
	private String value;
	
	@Override
	public Operand parse(Object... object) {
		if (object == null) {
			throw new IllegalArgumentException("参数不能为空");
		}
		if (object.length == 1)  {
			if(object[0] instanceof String) {
				value = (String)object[0];
			}		
		}  
		return this;
	}

	@Override
	public String value() {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public OperandEnum type() {
		// TODO Auto-generated method stub
		return OperandEnum.STRING;
	}

	@Override
	public void replace(Object oldValue, Object newValue) {
		if(Objects.equals(value, oldValue)){
			value = (String) newValue;
		}
	}

}
