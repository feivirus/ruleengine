package com.feivirus.ruleengine.enums;

public enum BeanAnnotationType {
	//冲突检测
	CONDITION_CHECKER(1),
	
	//普通规则匹配
	CONDITION_MATCH(2),
	
	//自定义取操作数,规则匹配
	CUSTOM_CONDITION_MATCH(3);
	
	private Integer value;
	
	private BeanAnnotationType(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public BeanAnnotationType getRuleAnnotationTypeByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for(BeanAnnotationType type : BeanAnnotationType.values()) {
			if (type.getValue().intValue() == value.intValue()) {
				return type;
			}
		}
		return null;
	}
}
