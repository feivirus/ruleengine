package com.feivirus.ruleengine.base;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

import com.feivirus.ruleengine.commission.CommissionRule;
import com.feivirus.ruleengine.commission.RouteComputeRuleCondition;
import com.feivirus.ruleengine.commission.RuleCondition;
import com.feivirus.ruleengine.enums.ConditionValidatorType;
import com.feivirus.ruleengine.enums.RelationEnum;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;
import com.feivirus.ruleengine.rule.dto.SysAreaDTO;
import com.feivirus.ruleengine.rule.dto.ValueRange;
import com.ideacome.common.enums.BusinessType;

/**
 * 规则冲突校验
 *
 * @author feivirus
 */
public class RuleValidator {

    /**
     * 校验两条规则是否冲突
     *
     * @param srcRule
     * @param targetRule
     * @return
     */
    public RelationEnum validateConflict(CommissionRule targetRule, CommissionRule srcRule) {
        if ((srcRule == null) || (targetRule == null)) {
            return null;
        }
        RelationEnum relationEnum = checkRuleRelation(targetRule, srcRule);
        return relationEnum;
    }


    /**
     * 适用地区冲突校验
     *
     * @param srcRule    老数据
     * @param targetRule 新增加数据
     * @return
     */
    public RelationEnum validateBusinessConflict(CommissionRule srcRule, CommissionRule targetRule) {
        // 校验适用地区
        RouteComputeRuleCondition srcRuleCondition = srcRule.getRouteComputeRuleCondition();
        RouteComputeRuleCondition targetRuleCondition = targetRule.getRouteComputeRuleCondition();

        List<String> srcSysAreaCodes = areaParamTransForm(srcRuleCondition.getSysAreaList());
        List<String> targetSysAreaCodes = areaParamTransForm(targetRuleCondition.getSysAreaList());
        RelationEnum areaRelationEnum = this.compare(ConditionValidatorType.AREA_CODE_CHECKER, srcSysAreaCodes, targetSysAreaCodes);
        return areaRelationEnum;
    }


    /**
     * 出单机构冲突校验
     *
     * @param srcRule
     * @param targetRule
     * @return
     */
    public RelationEnum validateOrganization(CommissionRule srcRule, CommissionRule targetRule) {
        RouteComputeRuleCondition srcRuleCondition = srcRule.getRouteComputeRuleCondition();
        RouteComputeRuleCondition targetRuleCondition = targetRule.getRouteComputeRuleCondition();
        Integer businessTypeCode = targetRuleCondition.getBusinessType();
        if (businessTypeCode != null) {
            BusinessType businessType = BusinessType.getBusinessTypeByCode(businessTypeCode);
            switch (businessType) {
                case OFFLINE:
                    String offlineSrcVO = srcRuleCondition.getAgencyListVO();
                    String offlineTargetVO = targetRuleCondition.getAgencyListVO();
                    return this.compare(ConditionValidatorType.STRING_CHECKER, offlineSrcVO, offlineTargetVO);
                case ONLINE:
                    List<String> srcSysAreaDTOList = areaParamTransForm(srcRuleCondition.getSysAreaDTOList());
                    List<String> targetSysAreaDTOList = areaParamTransForm(targetRuleCondition.getSysAreaDTOList());
                    return this.compare(ConditionValidatorType.AREA_CODE_CHECKER, srcSysAreaDTOList, targetSysAreaDTOList);
                default:
            }
        }
        return RelationEnum.UNKNOWN;
    }

    /**
     * 比较数据
     *
     * @param checkerType
     * @param srcValue
     * @param targetValue
     * @return
     */
    private RelationEnum compare(ConditionValidatorType checkerType, Object srcValue, Object targetValue) {
        RelationEnum relationEnum = RelationEnum.UNKNOWN;
        if (null != checkerType && null != srcValue) {
            ConditionChecker conditionCheckerAreaOnline = ConditionCheckerFactory.createRuleCondition(checkerType, srcValue, null);
            if (null != conditionCheckerAreaOnline) {
                relationEnum = conditionCheckerAreaOnline.compare(targetValue);
            }
        }
        return relationEnum;
    }

    /**
     * 参数转换
     *
     * @param sysAreaDTOS
     * @return
     */
    private List<String> areaParamTransForm(List<SysAreaDTO> sysAreaDTOS) {
        if (CollectionUtils.isNotEmpty(sysAreaDTOS)) {

        }
        List<String> srcSysAreaCodes = new ArrayList<>(sysAreaDTOS.size());
        for (SysAreaDTO srcParams : sysAreaDTOS) {
            srcSysAreaCodes.add(srcParams.getCode());
        }
        return srcSysAreaCodes;
    }

    /**
     * 规则有效期范围是否冲突
     *
     * @param srcRule
     * @param targetRule
     * @return
     */
    public RelationEnum ruleValidityCheck(CommissionRule srcRule, CommissionRule targetRule) {
        RouteComputeRuleCondition oldRuleCondition = srcRule.getRouteComputeRuleCondition();
        RouteComputeRuleCondition newRuleCondition = targetRule.getRouteComputeRuleCondition();
        ConditionValidatorType checkerType = ConditionValidatorType.DATE_VALUE_RANGE_CHECKER;
        // 旧数据有效期
        ValueRange<Date, Date> validity = new ValueRange<>(oldRuleCondition.getEffectiveDate(),
                oldRuleCondition.getExpirationDate(), null);
        // 获取校验器
        ConditionChecker conditionChecker = ConditionCheckerFactory.createRuleCondition(checkerType, validity, null);
        // 新数据有效期
        ValueRange<Date, Date> dateValueRange = new ValueRange<>(newRuleCondition.getEffectiveDate(),
                newRuleCondition.getExpirationDate(), null);
        RelationEnum relationEnum = conditionChecker.compare(dateValueRange);
        return relationEnum;
    }

    /**
     * 应收冲突检验
     *
     * @param srcRule    库存规则
     * @param targetRule 新建规则
     * @returni
     */
    public RelationEnum checkRuleRelation(CommissionRule targetRule, CommissionRule srcRule) {
        Map<String, RuleCondition> srcConditionMap = srcRule.getRuleConditionMap();
        Map<String, RuleCondition> targetConditionMap = targetRule.getRuleConditionMap();
        if (MapUtils.isEmpty(srcConditionMap) || MapUtils.isEmpty(targetConditionMap)) {
            return RelationEnum.UNKNOWN;
        }
        Map<String, PropertyInfo> srcPropertyInfoList = new HashMap<>();
        setPropertyInfoList(srcConditionMap, srcPropertyInfoList);
        Map<String, PropertyInfo> targetPropertyInfoList = new HashMap<>();
        setPropertyInfoList(targetConditionMap, targetPropertyInfoList);

        if (MapUtils.isEmpty(srcPropertyInfoList) && MapUtils.isEmpty(targetPropertyInfoList)) {
            return RelationEnum.UNKNOWN;
        }
        if (MapUtils.isNotEmpty(srcPropertyInfoList) && MapUtils.isEmpty(targetPropertyInfoList)) {
            return RelationEnum.SUBSET;
        }
        if (MapUtils.isEmpty(srcPropertyInfoList) && MapUtils.isNotEmpty(targetPropertyInfoList)) {
            return RelationEnum.CONTAIN;
        }
        // 规则维度的个数不相等直接判断为相离关系
        if (srcConditionMap.size() != targetConditionMap.size()) {
            return RelationEnum.SEPARATE;
        }
        int separateCount = 0; // 相离
        int intersectCount = 0; // 相交
        int containCount = 0; // 包含
        int subsetCount = 0; // 子集
        int differCount = 0; // 不同的
        int equalCount = 0; // 相同

        Iterator<Map.Entry<String, PropertyInfo>> targetEntries = targetPropertyInfoList.entrySet().iterator();
        while (targetEntries.hasNext()) {
            Map.Entry<String, PropertyInfo> targetEntry = targetEntries.next();
            String targetPropertyName = targetEntry.getKey();
            PropertyInfo targetPropertyInfo = targetEntry.getValue();

            ConditionValidatorType checkerType = targetPropertyInfo.getCheckerType();
            if (checkerType == null) {
                continue;
            }
            // 获取目标规则对象的校验器类型
            ConditionChecker conditionChecker = ConditionCheckerFactory.createRuleCondition(checkerType, targetPropertyInfo.getPropertyValue(),
                    OperatorEnum.getEnumByCode(targetPropertyInfo.getOperation()));
            if (conditionChecker == null) {
                continue;
            }
            // 判断原规则中是否包含key
            if (srcPropertyInfoList.containsKey(targetPropertyName)) {
                PropertyInfo srcPropertyInfo = srcPropertyInfoList.get(targetPropertyName);
                // 判断参数的额检验其类型是否相同
                if (targetPropertyInfo.getCheckerType().getValue() == srcPropertyInfo.getCheckerType().getValue()) {
                    // 参数转换
                    RelationEnum relationEnum = checkRuleRelation(conditionChecker, checkerType, srcPropertyInfo.getPropertyValue(),
                            OperatorEnum.getEnumByCode(srcPropertyInfo.getOperation()));
                    if (null != relationEnum) {
                        switch (relationEnum) {
                            case INTERSECT:
                                intersectCount++;
                                differCount++;
                                break;
                            case SEPARATE:
                                separateCount++;
                                differCount++;
                                break;
                            case CONTAIN:
                                containCount++;
                                differCount++;
                                break;
                            case SUBSET:
                                subsetCount++;
                                differCount++;
                                break;
                            case EQUAL:
                                equalCount++;
                                break;
                            default:
                                break;
                        }
                    }
                } else {
                    separateCount++;
                    differCount++;
                }
            } else {
                subsetCount++;
                differCount++;
            }
        }
        if (differCount == 0 &&
                equalCount > 0) {
            return RelationEnum.EQUAL;
        } else if (separateCount > 0) {
            return RelationEnum.SEPARATE;
        } else if (intersectCount > 0) {
            return RelationEnum.INTERSECT;
        } else if (containCount > 0) {
            return RelationEnum.CONTAIN;
        } else if (subsetCount > 0) {
            return RelationEnum.SUBSET;
        } else {
            return RelationEnum.UNKNOWN;
        }
    }

    /**
     * 根据校验器类型转换参数的数据类型
     *
     * @param ruleTermChecker 规则校验器
     * @param checkerType     校验器类型
     * @param param           操作数
     * @param operation       操作符
     * @return
     */
    private RelationEnum checkRuleRelation(ConditionChecker ruleTermChecker, ConditionValidatorType checkerType, Object param, OperatorEnum operation) {
        // 范围比较操作数个数
        int valueRangeNum = 2;
        RelationEnum relationEnum = null;
        switch (checkerType) {
            case STRING_CHECKER:
                if (param instanceof List) {
                    List<String> params = (List<String>) param;
                    StringBuffer buffer = new StringBuffer();
                    for (String pa : params) {
                        buffer.append(pa).append(",");
                    }
                    param = buffer.toString();
                }
                relationEnum = ruleTermChecker.compare(param);
                break;
            case AREA_CODE_CHECKER:
                if (param instanceof List) {
                    List<String> params = (List<String>) param;
                    relationEnum = ruleTermChecker.compare(params);
                }
                break;
            case INTEGER_CHECKER:
                if (param instanceof String) {
                    relationEnum = ruleTermChecker.compare(Integer.parseInt(param.toString()));
                } else if (param instanceof Integer) {
                    relationEnum = ruleTermChecker.compare(param);
                }
                break;
            case DOUBLE_CHECKER:
                if (param instanceof String) {
                    relationEnum = ruleTermChecker.compare(Double.parseDouble(param.toString()));
                } else if (param instanceof Double) {
                    relationEnum = ruleTermChecker.compare(param);
                }
                break;
            case DOUBLE_VALUE_RANGE_CHECKER:
                ValueRange<Double, Double> doubleRangeList = null;
                if (null != param && param instanceof String) {
                    String[] params = param.toString().split(",");
                    if (valueRangeNum == params.length) {
                        doubleRangeList = new ValueRange<>(Double.parseDouble(params[0]), Double.parseDouble(params[1]), operation);
                    }
                } else if (param instanceof ValueRange) {
                    doubleRangeList = (ValueRange<Double, Double>) param;
                    doubleRangeList.setOperation(operation);
                }
                relationEnum = ruleTermChecker.compare(doubleRangeList);
                break;
            case INTEGER_VALUE_RANGE_CHECKER:
                ValueRange<Integer, Integer> integerRangeList = null;
                if (null != param && param instanceof String) {
                    String[] params = param.toString().split(",");
                    if (valueRangeNum == params.length) {
                        integerRangeList = new ValueRange<>(Integer.parseInt(params[0]), Integer.parseInt(params[1]), operation);
                    }
                } else if (param instanceof ValueRange) {
                    integerRangeList = (ValueRange<Integer, Integer>) param;
                    integerRangeList.setOperation(operation);
                }
                relationEnum = ruleTermChecker.compare(integerRangeList);
                break;
            case DATE_VALUE_RANGE_CHECKER:
                ValueRange<Date, Date> dateRangeList = null;
                if (null != param && param instanceof String) {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String[] params = param.toString().split(",");
                    if (valueRangeNum == params.length) {
                        try {
                            dateRangeList = new ValueRange<>(format.parse(params[0]), format.parse(params[1]), null);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (param instanceof List) {
                    List<Date> dateRange = (List<Date>) param;
                    dateRangeList = new ValueRange<>(dateRange.get(0), dateRange.get(1), null);
                } else if (param instanceof ValueRange) {
                    dateRangeList = (ValueRange<Date, Date>) param;
                }
                relationEnum = ruleTermChecker.compare(dateRangeList);
                break;
            case PERSONGRADE_NUMBER_RANGE_CHECKER:
                ValueRange<Double, Double> personValueRange = null;
                if (param instanceof String) {
                    String[] params = param.toString().split(",");
                    if (valueRangeNum == params.length) {
                        personValueRange = new ValueRange<>(Double.parseDouble(params[0]), Double.parseDouble(params[1]), operation);
                    }
                } else if (param instanceof ValueRange) {
                    personValueRange = (ValueRange<Double, Double>) param;
                    personValueRange.setOperation(operation);
                }
                relationEnum = ruleTermChecker.compare(personValueRange);
                break;
            case PERSONGRADE_NUMBER_VALUE_CHECKER:
                Double personNumberValue = null;
                if (param instanceof Double) {
                    personNumberValue = (Double) param;
                } else if (param instanceof String) {
                    personNumberValue = Double.parseDouble(param.toString());
                }
                relationEnum = ruleTermChecker.compare(personNumberValue);
                break;
            case BOOLEAN_CHECKER:
                if (param instanceof Boolean) {
                    relationEnum = ruleTermChecker.compare(param);
                } else if (param instanceof String) {
                    String val = param.toString();
                    if (Boolean.TRUE.toString().equals(val)) {
                        relationEnum = ruleTermChecker.compare(Boolean.TRUE);
                    } else if (Boolean.FALSE.toString().equals(val)) {
                        relationEnum = ruleTermChecker.compare(Boolean.FALSE);
                    }
                }
            default:
        }
        return relationEnum;
    }

    /**
     * 设置对象转换信息
     *
     * @param condtionMap
     * @param propertyInfoList
     */
    private void setPropertyInfoList(Map<String, RuleCondition> condtionMap, Map<String, PropertyInfo> propertyInfoList) {
        Iterator iterator = condtionMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, RuleCondition> entry = (Map.Entry<String, RuleCondition>) iterator.next();
            RuleCondition ruleCondition = entry.getValue();
            Map<String, PropertyInfo> itemPropertyInfo = doExtractPropertyInfoWithCheckerType(ruleCondition);
            propertyInfoList.putAll(itemPropertyInfo);
        }
    }


    /**
     * 返回带有冲突检测字段的属性信息
     *
     * @param t
     * @return
     */
    public <T extends RuleCondition> Map<String, PropertyInfo> doExtractPropertyInfoWithCheckerType(T t) {
        Map<String, PropertyInfo> propertyMap = new HashMap<>();
        if (t == null) {
            return propertyMap;
        }
        String fieldName = t.getTermCode();
        ConditionValidatorType checkerType = t.validateType();

        if (checkerType != null) {
            PropertyInfo propertyInfo = new PropertyInfo();

            propertyInfo.setPropertyName(fieldName);
            propertyInfo.setCheckerType(checkerType);
            propertyInfo.setOperation(t.getOperation());
            Object propertyValue = t.getOperand();
            if (propertyValue != null) {
                propertyInfo.setPropertyValue(propertyValue);
            }
            propertyMap.put(fieldName, propertyInfo);
        }

        return propertyMap;
    }
}
