package com.feivirus.ruleengine.base;

import com.feivirus.ruleengine.enums.RelationEnum;

public interface ConditionChecker<T> {
	//判断两个条件的关系，用于入库时的冲突检测
	RelationEnum compare(T target);	
}
