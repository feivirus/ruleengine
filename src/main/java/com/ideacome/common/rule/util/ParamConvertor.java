package com.ideacome.common.rule.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;
import com.feivirus.ruleengine.rule.dto.ConditionTerm;
import com.ideacome.common.enums.AppMark;
import com.ideacome.common.enums.GroupTypeEnum;

public class ParamConvertor {
	public static final String OPERATOR_SPLITTER = ",";

	public static List<ConditionTerm> convert2OperandList(String termJson) {
		if (StringUtils.isBlank(termJson)) {
			return null;
		}
		JSONArray jsonArray = JSON.parseArray(termJson);
		List<ConditionTerm> conditionTermList = new ArrayList<>();

		for(Object obj : jsonArray) {
			JSONObject jsonObject = (JSONObject)obj;
			ConditionTerm term = new ConditionTerm();

			term.setCode(jsonObject.getString("code"));
			term.setName(jsonObject.getString("name"));
			conditionTermList.add(term);
		}
		return conditionTermList;
	}

	public static List<ConditionTerm> convert2OperatorList(String termJson) {
		if (StringUtils.isBlank(termJson)) {
			return null;
		}
		String[] operatorArr = termJson.split(OPERATOR_SPLITTER);
		List<ConditionTerm> conditionTerms =new ArrayList<>();
		if(null!=operatorArr && operatorArr.length>0){
			for(int i=0;i<operatorArr.length;i++){
				ConditionTerm conditionTerm = new ConditionTerm();
				conditionTerm.setCode(operatorArr[i]);
				OperatorEnum operatorEnum = OperatorEnum.getEnumByCode(operatorArr[i]);
				conditionTerm.setName(operatorEnum == null ? null : operatorEnum.getName());
				conditionTerms.add(conditionTerm);
			}
		}
		return conditionTerms;
	}
	
	public static Integer[] convert2IntegerList(String operandValue) {
		if (StringUtils.isBlank(operandValue)) {
			return null;
		}
		String[] operandArr = operandValue.split(operandValue);
		List<Integer> operandList = new ArrayList<>();
		
		if(operandArr != null && operandArr.length > 0) {
			for(String operandItem : operandArr) {
				if (NumberUtils.isNumber(operandItem)) {
					operandList.add(Integer.valueOf(operandItem));
				}
			}
			return operandList.toArray(new Integer[operandList.size()]);
		}
		return null;
	}
	
	/**
	 * 比较AppMark,GroupType
	 * @param appMarkValue
	 * @param groupTypeValue
	 * @return
	 */
	public static boolean compareAppMark2GroupTypeEnum(Integer appMarkValue, Integer groupTypeValue) {
		AppMark appMark = AppMark.getAppMarkByValue(appMarkValue);
		GroupTypeEnum groupType = GroupTypeEnum.getGroupTypeEnum(groupTypeValue);
		
		if (appMark == null || groupType == null) {
			return false;
		}
		if (appMark == AppMark.XFX && (groupType == GroupTypeEnum.GROUP))  {
			return true;
		}
		if ((appMark == AppMark.SHELL || appMark == AppMark.WXB || appMark == AppMark.XYJ) && 
			(groupType == GroupTypeEnum.CHANNEL || groupType == GroupTypeEnum.SCENE)) {
			return true;
		}
		return false;
	}
	
	public static String[] splitParametersWithComma(String parameter) {
		if (StringUtils.isBlank(parameter)) {
			return null;
		}
		String[] paramArr = parameter.split(OPERATOR_SPLITTER);
		return paramArr;
	}	 
}
