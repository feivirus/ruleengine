package com.feivirus.ruleengine.base;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.spi.Bean;

import org.apache.commons.lang.StringUtils;

import com.feivirus.ruleengine.enums.BeanAnnotationType;
import com.feivirus.ruleengine.enums.ConditionCodeEnum;
import com.feivirus.ruleengine.enums.ConditionValidatorType;
import com.feivirus.ruleengine.enums.CustomOperandValueEnum;
import com.feivirus.ruleengine.enums.instruction.OperandEnum;

/**
 * 
 * @author feivirus
 *
 */
public class BeanTool {		
	
	/**
	 * 返回带有注解的属性信息
	 * @param t
	 * @param annotationType
	 * @return
	 */
	public static <T> Map<String, PropertyInfo> doExtractPropertyInfoWithAnnotation(T t, BeanAnnotationType annotationType) {
		Map<String, PropertyInfo> propertyMap = new HashMap<>();
		if (t == null) {
			return propertyMap;
		}

		Class<?> clazz = t.getClass();
		Field[] fields = clazz.getDeclaredFields();

		for(Field field : fields) {
			boolean exists = false;
			PropertyInfo propertyInfo = new PropertyInfo();
			
			if (annotationType == BeanAnnotationType.CONDITION_CHECKER) {
				ConditionCheck checker = field.getAnnotation(ConditionCheck.class);

				if (checker != null) {
					propertyInfo.setCheckerType(checker.checkType());	
					exists = true;
				}
			}
			if (annotationType == BeanAnnotationType.CONDITION_MATCH) {
				NormalConditionMatch matcher = field.getAnnotation(NormalConditionMatch.class);
				
				if (matcher != null) {
					propertyInfo.setConditionCodeEnum(matcher.conditionCode());
					exists = true;
				}
			}
			if (annotationType == BeanAnnotationType.CUSTOM_CONDITION_MATCH) {
				CustomConditionMatch matcher = field.getAnnotation(CustomConditionMatch.class);
				
				if (matcher != null) {
					propertyInfo.setCustomOperandValueEnum(matcher.customType());
					exists = true;
				}
			}
			
			if (exists == true) {
				String fieldName = field.getName();
				propertyInfo.setPropertyName(fieldName);
				
				Object propertyValue = getPropertyValue(t, fieldName);
				if (propertyValue != null) {
					propertyInfo.setPropertyValue(propertyValue);
				}
				if (annotationType == BeanAnnotationType.CONDITION_CHECKER) {
					propertyMap.put(fieldName, propertyInfo);
				}	
				if (annotationType == BeanAnnotationType.CONDITION_MATCH) {
					ConditionCodeEnum codeEnum = propertyInfo.getConditionCodeEnum();
					if (codeEnum != null) {
						propertyMap.put(codeEnum.getCode(), propertyInfo);
					}
				}
				if (annotationType == BeanAnnotationType.CUSTOM_CONDITION_MATCH) {
					CustomOperandValueEnum valueEnum = propertyInfo.getCustomOperandValueEnum();
					if (valueEnum != null) {
						propertyMap.put(valueEnum.getCode(), propertyInfo);
					}
				}
			}
		}

		return propertyMap;
	}
	
	public static <T> boolean putInFieldValue(T t, String annotationName, Object value) {
		if (t == null || StringUtils.isEmpty(annotationName)) {
			return false;
		}
		String fieldName = getFieldName(t, BeanAnnotationType.CONDITION_MATCH, annotationName);
		
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			
			for(PropertyDescriptor descriptor : propertyDescriptors) {
				if (descriptor.getName().equals(fieldName)) {
					
					Method writeMethod = descriptor.getWriteMethod();
					
					if (writeMethod != null) {
						writeMethod.invoke(t, value);						
					} else {
						descriptor.setValue(fieldName, value);
					}
					return true;
				}
			}
		} catch (Exception e){			
		}
		return false;
	}
	
	/**
	 * 
	 * @param t
	 * @param type
	 * @return 带有指定注解的字段名
	 */
	public static <T> String getFieldName(T t, BeanAnnotationType type, String annotationName) {
		Class<?> clazz = t.getClass();
		Field[] fields = clazz.getDeclaredFields();		

		for(Field field : fields) {
			if (type == BeanAnnotationType.CONDITION_MATCH) {
				NormalConditionMatch matcher = field.getAnnotation(NormalConditionMatch.class);
				
				if (matcher != null && matcher.conditionCode().getCode().equals(annotationName)) {
					return field.getName();
				}				
			}
		}
		return null;
	}
	
	public static <T> Object getPropertyValue(T t, String fieldName) {
		if (StringUtils.isBlank(fieldName)) {
			return null;
		}
		Object value = null;

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
			PropertyDescriptor[] propertyDescriptorList = beanInfo.getPropertyDescriptors();

			for(PropertyDescriptor descriptor : propertyDescriptorList) {
				if (fieldName.equals(descriptor.getName())) {
					Method method = descriptor.getReadMethod();

					if (method != null) {
						value = method.invoke(t, null);
						break;
					} else {
						value = descriptor.getValue(fieldName);
					}
				}
			}

		} catch (Exception e) {
		}
		return value;
	}
}
