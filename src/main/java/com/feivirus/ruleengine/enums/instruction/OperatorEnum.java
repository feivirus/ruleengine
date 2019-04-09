package com.feivirus.ruleengine.enums.instruction;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Case;

/**
 * 
 * @author feivirus
 *
 */
public enum OperatorEnum {
	EQ("eq", "等于"),
	
	NEQ("neq", "不等于"),	
	
	GT("gt", "大于"),
	
	EGT("egt", "大于等于"),
	
	LT("lt", "小于"),
	
	ELT("elt", "小于等于"),
	
	IN("in", "包含"),	

	NOT_IN("not_in", "不包含"),
	
	PLUS("add", "加"),
	
	MINUS("minus", "减"),
	
	MULTIPLY("multiply", "乘"),
			
	DEVIDED("devided", "除"),

	// 例如：1 <= x <= 10
	EQ_BETWEEN("eq_between","全闭区间"),

	// 例如：1 < x < 10
	NOT_EQ_BETWEEN("not_eq_between","全开区间"),

	// 例如：1 <= x < 10
	LEFT_EQ_BETWEEN("left_eq_between","左闭右开"),

	// 例如：1 < x <= 10
	RIGHT_EQ_BETWEEN("right_eq_between","左开右闭"),

	/**
	 * 下面的不参与实际匹配,前端处理用
	 */
	
	/*数值--范围是*/
	NUMBER_RANGE("number_range","范围是"),
	
	/*数值--取值是*/
	NUMBER_VALUE("number_value","取值是");

	private final String code;
	
	private final String name;	

	OperatorEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}	
	
	public static OperatorEnum getEnumByCode(String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		}
		
		for(OperatorEnum enumItem : OperatorEnum.values()) {
			if (enumItem.getCode().equalsIgnoreCase(value)) {
				return enumItem;
			}
		}
		
		return null;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}	
	
	/**
	 * 对应的操作数是否多个
	 * @param operatorEnum
	 */
	public static boolean isValueRangeOperand(OperatorEnum operatorEnum) {
		boolean result = true;
		if (operatorEnum == null) {
			return result;
		}		 
		switch (operatorEnum) {
			case EQ:
			case NEQ:
			case GT:
			case EGT:
			case LT:
			case ELT:
			{
				result = false;
				break;
			}
		default:
			break;
		}
		return result;
	}
}
