package com.feivirus.ruleengine.base.instruction.operand;

import java.util.Date;
import java.util.Objects;

import com.feivirus.ruleengine.base.instruction.Operand;
import com.feivirus.ruleengine.base.instruction.Operation;
import com.feivirus.ruleengine.enums.instruction.OperandEnum;

public class DateOperand implements Operand<Date>, Operation {
	private Date value;

	@Override
	public Operand parse(Object... object) {
		if (object == null) {
			throw new IllegalArgumentException("参数不能为空");
		}
		if (object.length == 1)  {
			if(object[0] instanceof String) {
				
			}
			
			if (object[0] instanceof Date) {
				value = (Date)object[0];
			}
		}  
		return this;
	}

	@Override
	public Date value() {
		return value;
	}

	@Override
	public OperandEnum type() {		
		return OperandEnum.DATE;
	}

	@Override
	public void replace(Object oldValue, Object newValue) {
		if(Objects.equals(value, oldValue)){
			value = (Date) newValue;
		}
	}
}
