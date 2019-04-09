package com.feivirus.ruleengine.base.instruction.operand;

import java.util.Map;

import com.feivirus.ruleengine.base.instruction.CustomOperand;
import com.feivirus.ruleengine.enums.ConditionCodeEnum;

/**
 * 
 * @author feivirus
 * 用于其他系统传入规则引擎时，传入的参数和数据库里面的code不对应，需要解析
 * 返回支持的条件code,对应code的value
 */
public interface CustomOperandValue {

	Map<ConditionCodeEnum, CustomOperand> parse(Object value, String companyCode);

}
