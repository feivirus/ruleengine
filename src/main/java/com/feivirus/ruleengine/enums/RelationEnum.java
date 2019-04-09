package com.feivirus.ruleengine.enums;

/**
 * 
 * @author feivirus
 *
 */
public enum RelationEnum {
	UNKNOWN(0),		//未知关系
	INTERSECT(1),	//相交关系
	SEPARATE(2),	//相离关系
	CONTAIN(3),	  //包含关系 前者包含后者
	SUBSET(4),	//子集关系 后者包含前者
	EQUAL(5);	//相等关系  
	
	
	private int value;	
	
	RelationEnum(int value) {
		setValue(value);
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}		
}
