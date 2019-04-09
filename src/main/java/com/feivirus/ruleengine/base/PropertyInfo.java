package com.feivirus.ruleengine.base;

import java.lang.reflect.Method;

import com.feivirus.ruleengine.base.instruction.OperandExtend;
import com.feivirus.ruleengine.enums.ConditionCodeEnum;
import com.feivirus.ruleengine.enums.ConditionValidatorType;
import com.feivirus.ruleengine.enums.CustomOperandValueEnum;
import com.feivirus.ruleengine.enums.instruction.OperandEnum;

public class PropertyInfo {
	//类中字段名
	private String propertyName;

	//类中字段的getter方法
	private Method readMethod;

    //用户选择的操作符,比如between,@link OperatorEnum
    private String operation;

	//类中字段的值
	private Object propertyValue;

	private OperandExtend operandExtend;

	//属性的校验类型
	private ConditionValidatorType checkerType;
	
	//规则匹配的条件
	private ConditionCodeEnum conditionCodeEnum;
	
	//自定义获取数据类型的规则匹配
	private CustomOperandValueEnum customOperandValueEnum;

	public String getPropertyName() {
		return propertyName;
	}

	public Method getReadMethod() {
		return readMethod;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public void setReadMethod(Method readMethod) {
		this.readMethod = readMethod;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Object getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(Object propertyValue) {
		this.propertyValue = propertyValue;
	}

	public OperandExtend getOperandExtend() {
		return operandExtend;
	}

	public void setOperandExtend(OperandExtend operandExtend) {
		this.operandExtend = operandExtend;
	}

	public ConditionValidatorType getCheckerType() {
		return checkerType;
	}

	public void setCheckerType(ConditionValidatorType checkerType) {
		this.checkerType = checkerType;
	}

	public ConditionCodeEnum getConditionCodeEnum() {
		return conditionCodeEnum;
	}

	public void setConditionCodeEnum(ConditionCodeEnum conditionCodeEnum) {
		this.conditionCodeEnum = conditionCodeEnum;
	}

	public CustomOperandValueEnum getCustomOperandValueEnum() {
		return customOperandValueEnum;
	}

	public void setCustomOperandValueEnum(CustomOperandValueEnum customOperandValueEnum) {
		this.customOperandValueEnum = customOperandValueEnum;
	}
}
