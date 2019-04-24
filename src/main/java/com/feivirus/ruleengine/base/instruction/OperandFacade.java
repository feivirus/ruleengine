package com.feivirus.ruleengine.base.instruction;

import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.feivirus.common.rule.util.ParamConvertor;
import com.feivirus.ruleengine.base.instruction.operand.DateOperand;
import com.feivirus.ruleengine.base.instruction.operand.DoubleOperand;
import com.feivirus.ruleengine.base.instruction.operand.IntegerOperand;
import com.feivirus.ruleengine.base.instruction.operand.StringOperand;
import com.feivirus.ruleengine.base.instruction.operand.ValueRangeOperand;
import com.feivirus.ruleengine.enums.instruction.OperandEnum;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

/**
 * 
 * @author feivirus
 *
 * @param <T>
 *
 * 做多参数转换
 */
public class OperandFacade<T> implements Operand<T>{
	 Operand<T> ruleOperand;	
	 
	 //用于普通数据类型的衍生类型，比如区间类型,判断区间的数据类型是Integer,还是String等
	 //比如整数1, 字符串1，都可以用来做数字1，在IntegerOperand内部判断
	 OperandEnum operandEnum;
	 
	 //用于区分生成是Interger,还是ValueRangeOperand
	 OperatorEnum operatorEnum;

	public OperandFacade(OperandEnum typeEnum, OperatorEnum operatorEnum) {
		ruleOperand = typeEnum.getInstance();		
		this.operandEnum = typeEnum;
		this.operatorEnum = operatorEnum;
	}
	 
	 /**
	  * 多个参数解析到具体RuleOperand中,比如ValueRange类型的
	  * @param object
	  * @return
	  */
	@Override
	public Operand parse(Object... object) {
		if (object == null) {
			throw new IllegalArgumentException("参数不能为空");
		}
		if (OperatorEnum.isValueRangeOperand(operatorEnum)) {
			ruleOperand = parseValueRangeArgument(object);			
		} else {
			ruleOperand.parse(object);
		}
		
		return ruleOperand;
	}	
	
	public Operand parseValueRangeArgument(Object[] objects) {
		if (objects == null || objects.length < 1) {
			return null;
		}
		if (objects.length == 1) {
			if(objects[0] instanceof List) {
				objects = filterParameter((List)(objects[0]));
			}else{
				objects = filterParameter(objects[0].toString());
			}
		}
		Object[] newParameter = new Object[objects.length];
		
		if (operandEnum == OperandEnum.INTEGER) {			
			for(int i = 0; i < objects.length; i++) {
				Operand<Integer> operandItem = new IntegerOperand().parse(objects[i]);
				
				newParameter[i] = operandItem;
			}
			return new ValueRangeOperand<IntegerOperand>().parse(newParameter);
		}
		if (operandEnum == OperandEnum.DOUBLE) {
			for(int i = 0; i < objects.length; i++) {
				Operand<Double> operandItem = new DoubleOperand().parse(objects[i]);
				
				newParameter[i] = operandItem;
			}
			return new ValueRangeOperand<DoubleOperand>().parse(newParameter);
		}
		if (operandEnum == OperandEnum.STRING) {
			for(int i = 0; i < objects.length; i++) {
				Operand<String> operandItem = new StringOperand().parse(objects[i]);
				
				newParameter[i] = operandItem;
			}
			return new ValueRangeOperand<StringOperand>().parse(newParameter);
		}
		if (operandEnum == OperandEnum.DATE) {
			for(int i = 0; i < objects.length; i++) {
				Operand<Date> operandItem = new DateOperand().parse(objects[i]);
				
				newParameter[i] = operandItem;
			}
			return new ValueRangeOperand<DateOperand>().parse(newParameter);
		}
		return null;
	}

	@Override
	public T value() {
		return ruleOperand.value();
	}

	@Override
	public OperandEnum type() {
		// TODO Auto-generated method stub
		return operandEnum;
	}
	
	public String[] filterParameter(String operand) {
		if (StringUtils.isBlank(operand)) {
			return null;
		}
		String [] result = null;
		if (operand.contains(ParamConvertor.OPERATOR_SPLITTER)) {
			result = ParamConvertor.splitParametersWithComma(operand);
			return result;
		} else {
			result = new String[1];
			result[0] = operand;
			return result;
		}
	}

	public Object[] filterParameter(List list){
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return list.toArray();
	}
}
