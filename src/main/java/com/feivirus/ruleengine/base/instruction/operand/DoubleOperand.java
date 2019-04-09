package com.feivirus.ruleengine.base.instruction.operand;

import java.util.Date;
import java.util.Objects;

import com.feivirus.ruleengine.base.instruction.Operand;
import com.feivirus.ruleengine.base.instruction.Operation;
import com.feivirus.ruleengine.enums.instruction.OperandEnum;

public class DoubleOperand implements Operand<Double>, Operation {
	private Double value;

	@Override
	public Double value() {
		return value;
	}

	@Override
	public OperandEnum type() {
		return OperandEnum.DOUBLE;
	}

	@Override
	public Operand parse(Object... object) {
		if (object == null) {
			throw new IllegalArgumentException("参数不能为空");
		}
		if (object.length == 1)  {
			if(object[0] instanceof String) {
				value = Double.valueOf((String)object[0]);
			}
			
			if (object[0] instanceof Double) {
				value = Double.valueOf((double)object[0]);
			}
		}  
		return this;
	}

	@Override
	public void replace(Object oldValue, Object newValue) {
		if(Objects.equals(value, oldValue)){
			value = (Double) newValue;
		}
	}

	@Override
	public void multiply(Integer multiple) {
		if(multiple == null){
			return;
		}
		if(multiple > 0){
			value = value * Math.abs(multiple);
			return;
		}
		if(multiple < 0){
			value = value / Math.abs(multiple);
			return;
		}
	}
}
