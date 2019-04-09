package com.feivirus.ruleengine.base.instruction.operand;

import java.util.ArrayList;
import java.util.List;

import com.feivirus.ruleengine.base.instruction.Operand;
import com.feivirus.ruleengine.base.instruction.Operation;
import com.feivirus.ruleengine.enums.instruction.OperandEnum;

/**
 * 
 * @author feivirus
 * 
 * @param <P>
 */
public class ValueRangeOperand<P extends Operand> implements Operand<ValueRangeOperand<P>>,Operation{
	private List<P> operandList;	

	@Override
	public Operand parse(Object... object) {
		if (object == null) {
			return null;
		}			
		if (operandList == null) {
			operandList = new ArrayList<>();			
		}
		for(Object objItem : object) {
			operandList.add((P)objItem);
		}
		return this;
	}

	@Override
	public OperandEnum type() {
		if (operandList == null) {
			return null;
		}
		P firstOperand = operandList.get(0);
		if (firstOperand instanceof IntegerOperand) {
			return OperandEnum.VALUE_RANCE_INTEGER;
		} else if (firstOperand instanceof DoubleOperand) {
			return OperandEnum.VALUE_RANGE_DOUBLE;
		} else if (firstOperand instanceof DateOperand) {
			return OperandEnum.VALUE_RANGE_DATE;
		} else if (firstOperand instanceof StringOperand) {
			return OperandEnum.STRING;
		}
		return null;
	}	
	
	@Override
	public ValueRangeOperand<P> value() {
		return this;
	}

	public List<P> getOperandList() {
		return operandList;
	}

	public void setOperandList(List<P> operandList) {
		this.operandList = operandList;
	}

	@Override
	public void replace(Object oldValue, Object newValue) {
		for(Operand operand : operandList){
			if(operand instanceof Operation){
				((Operation)operand).replace(oldValue, newValue);
			}
		}
	}

	@Override
	public void multiply(Integer multiple) {
		for(Operand operand : operandList){
			if(operand instanceof Operation){
				((Operation)operand).multiply(multiple);
			}
		}
	}
}
