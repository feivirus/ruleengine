package com.feivirus.ruleengine.enums;

/**
 * 
 * @author feivirus
 *
 */
public enum CommissionRuleTermCategoryEnum {
	CAR_RULE_CONDITION("carRuleCondition", "车辆属性", 1),
	
	COMPANY_PACKAGE_RULE_CONDITION("companyPackageRuleCondition", "险企属性", 2),
	
	PERSON_RULE_CONDITION("personRuleCondition", "人员属性", 3);
	
	private CommissionRuleTermCategoryEnum(String code, String name, Integer value) {
		this.name = name;
		this.code = code;
		this.value = value;
	}

	private String name;
	
	private String code;
	
	private Integer value;

	public static CommissionRuleTermCategoryEnum getCategoryEnumByValue(Integer value) {
		if (value == null) {
			return null;
		}
		for(CommissionRuleTermCategoryEnum categoryEnum : CommissionRuleTermCategoryEnum.values()) {
			if (categoryEnum.value.intValue() == value.intValue()) {
				return categoryEnum;
			}
		}
		return null;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}
