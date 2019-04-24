package com.feivirus.common.rule.util;

import java.math.BigDecimal;

public class NumberUtil {
	public static boolean isNumber(Object sourceNumber, Object targetNumber) {
		if (sourceNumber != null && 
			targetNumber != null && 
			sourceNumber instanceof Number &&
			targetNumber instanceof Number) {
			return true;
		}
		return false;
	}
	
	public static double toDouble(Object value) {
		if (value == null || 
			!(value instanceof Number)) {
			throw new  IllegalArgumentException("参数为空或者不是数字");
		}
		return new BigDecimal(value.toString()).doubleValue();
	}
}
