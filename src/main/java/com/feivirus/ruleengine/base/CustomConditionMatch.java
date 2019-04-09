package com.feivirus.ruleengine.base;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.feivirus.ruleengine.enums.CustomOperandValueEnum;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomConditionMatch {
	CustomOperandValueEnum customType();
}
