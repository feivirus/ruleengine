package com.feivirus.ruleengine.base;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.feivirus.ruleengine.enums.ConditionValidatorType;

/**
 * 
 * @author feivirus
 * 基于注解的组合模式
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConditionCheck{
	 ConditionValidatorType checkType() default ConditionValidatorType.STRING_CHECKER;
}
