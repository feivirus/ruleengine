package com.feivirus.ruleengine.enums;

/**
 * 
 * @author feivirus
 *
 */
public enum CommissionComputeTypeEnum {
	//佣金比例
	COMPUTE_TYPE_RATIO(1),
	
	//一车一价
	COMPUTE_TYPE_ONE_CAR_ONE_RATIO(2),

	//指定点数
	@Deprecated
	COMPUTER_TYPE_SPECIFIC_RATIO(3);
	
	private int value;
	
	private CommissionComputeTypeEnum(int value) {
		this.value = value;
	}
	
	public int value() {
		return this.value;
	}
	
	public static CommissionComputeTypeEnum getCommissionType(Integer value) {
		if (value == null) {
			return null;
		}
		switch (value) {
		case 1:
			return COMPUTE_TYPE_RATIO;
		case 2:
			return COMPUTE_TYPE_ONE_CAR_ONE_RATIO;
		case 3:
			return COMPUTER_TYPE_SPECIFIC_RATIO;
		default:
			return null;
		}
	}
}
