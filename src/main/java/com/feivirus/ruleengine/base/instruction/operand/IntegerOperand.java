package com.feivirus.ruleengine.base.instruction.operand;

import java.util.Objects;

import com.feivirus.ruleengine.base.instruction.Operand;
import com.feivirus.ruleengine.base.instruction.Operation;
import com.feivirus.ruleengine.enums.instruction.OperandEnum;

/**
 * 
 * @author feivirus
 *
 */
public class IntegerOperand implements Operand<Integer>, Operation {
	private Integer value;

	@Override
	public Integer value() {
		return value;
	}

	@Override
	public OperandEnum type() {
		return OperandEnum.INTEGER;
	}

	@Override
	public Operand parse(Object... object) {
		if (object == null) {
			throw new IllegalArgumentException("参数不能为空");
		}
		if (object.length == 1)  {
			if(object[0] instanceof String) {
				value = Integer.valueOf((String)object[0]);
			}
			
			if (object[0] instanceof Integer) {
				value = Integer.valueOf((int)object[0]);
			}
		}  
		return this;
	}

	@Override
	public void replace(Object oldValue, Object newValue) {
		if(Objects.equals(value, oldValue)){
			value = (Integer) newValue;
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
