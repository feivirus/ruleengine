package com.feivirus.ruleengine.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author feivirus
 *
 */
public enum CustomOperandValueEnum {
	ORDER_KIND_ITEM(1, "orderKindItem");
	
	private int value;
	
	private String code;
	
	private CustomOperandValueEnum() {
	}
	
	private CustomOperandValueEnum(int value, String code) {
		this.code = code;
		this.value = value;
	}

	public int getValue() {
		return value;
	}	

	public String getCode() {
		return code;
	}
	
	public static CustomOperandValueEnum getEnumByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for(CustomOperandValueEnum valueEnumItem : CustomOperandValueEnum.values()) {
			if (valueEnumItem.value == value.intValue()) {
				return valueEnumItem;
			}
		}
		return null;
	}
	
	public static CustomOperandValueEnum getEnumByCode(String code) {
		if (StringUtils.isBlank(code)) {
			return null;
		}
		for(CustomOperandValueEnum valueEnumItem : CustomOperandValueEnum.values()) {
			if (valueEnumItem.getCode().equals(code)) {
				return valueEnumItem;
			}
		}
		return null;
	}
}
