package com.feivirus.ruleengine.enums.instruction;

import com.feivirus.ruleengine.base.instruction.Operand;
import com.feivirus.ruleengine.base.instruction.operand.DateOperand;
import com.feivirus.ruleengine.base.instruction.operand.DoubleOperand;
import com.feivirus.ruleengine.base.instruction.operand.IntegerOperand;
import com.feivirus.ruleengine.base.instruction.operand.StringOperand;
import com.feivirus.ruleengine.base.instruction.operand.ValueRangeOperand;

public enum OperandEnum {
	STRING(1, "string") {
		@Override
		public Operand getInstance() {
			return new StringOperand();
		}
	},
	INTEGER(2, "integer") {
		@Override
		public Operand getInstance() {
			return new IntegerOperand();
		}		
	},
	DOUBLE(3, "double") {
		@Override
		public Operand getInstance() {			
			return new DoubleOperand();
		}		
	},
	DATE(4, "date") {
		@Override
		public Operand getInstance() {
			// TODO Auto-generated method stub
			return new  DateOperand();
		}		
	},
	VALUE_RANCE_INTEGER(5, "value_range_integer") {
		@Override
		public Operand getInstance() {
			// TODO Auto-generated method stub
			return new ValueRangeOperand<IntegerOperand>();
		}
	},
	VALUE_RANGE_DOUBLE(6, "value_range_double") {
		@Override
		public Operand getInstance() {
			return new ValueRangeOperand<DoubleOperand>();
		}
	},
	VALUE_RANGE_DATE(7, "value_range_date") {
		@Override
		public Operand getInstance() {
			// TODO Auto-generated method stub
			return new ValueRangeOperand<DateOperand>();
		}		
	};	
	
	private int value;
	
	private String code;

	OperandEnum(int value, String code) {
		this.value = value;
		this.code = code;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public abstract Operand getInstance();
	
	public static OperandEnum getOperandEnumByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for(OperandEnum operand : OperandEnum.values()) {
			if (operand.getValue() == value.intValue()) {
				return operand;
			}
		}
		return null;
	}
	
	public static OperandEnum getOperandEnumByOperatorEnum(OperatorEnum operatorEnum) {
		if (operatorEnum == null) {
			return null;
		}
		
			
		
		return null;
	}
}
