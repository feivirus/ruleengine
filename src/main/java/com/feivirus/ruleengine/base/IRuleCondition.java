package com.feivirus.ruleengine.base;

import java.io.Serializable;
import java.util.List;

import com.feivirus.ruleengine.base.instruction.Operand;
import com.feivirus.ruleengine.enums.CommissionRuleTermCategoryEnum;
import com.feivirus.ruleengine.enums.ConditionValidatorType;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

/**
 * 
 * @author feivirus
 * 规则包含各种规则条件,规则条件由规则术语+指令组成，指令由操作符+操作数组成.
 * 规则入库前需要针对术语+支持的指令部分一起通过校验器做冲突检测,
 * 
 */
public interface IRuleCondition extends Serializable{
	// 名称，唯一,比如转续保
	String name();

	//编码，唯一, 比如renewalEnum
	String code();
	
	//计量单位,比如元
	String unit();
	
	//上级目录标识 , 比如车，人，保险公司
	CommissionRuleTermCategoryEnum category();
	
	//可用的操作符
	List<OperatorEnum> availableOperators();
	
	//可用的操作数
	List<Operand> availableOperands();
	
	//冲突检测的类型
	ConditionValidatorType conflictCheckerType();
}

