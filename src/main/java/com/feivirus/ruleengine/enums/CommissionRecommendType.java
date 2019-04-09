package com.feivirus.ruleengine.enums;

/**
 * 
 * @author feivirus
 *
 */
public enum CommissionRecommendType {
	 	BY_AGENT(1, "入帮推荐"),
	 	
	    LEADER_ALLOWANCE(2, "帮主管理津贴");

	    private Integer value;
	    private String name;

	    CommissionRecommendType(Integer value, String name) {
	        this.value = value;
	        this.name = name;
	    }

	    public Integer getValue() {
	        return value;
	    }

	    public String getName() {
			return name;
		}

		public static CommissionRecommendType getRecommendType(Integer value) {
	        if(value != null){
	            for (CommissionRecommendType commissionType : CommissionRecommendType.values()) {
	                if (value.intValue() == commissionType.getValue()) {
	                    return commissionType;
	                }
	            }
	        }
	        return null;
	    }
}
