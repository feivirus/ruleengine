package com.feivirus.ruleengine.base;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.feivirus.ruleengine.base.checker.AreaConditionChecker;
import com.feivirus.ruleengine.base.checker.BooleanConditionChecker;
import com.feivirus.ruleengine.base.checker.DateValueRangeConditionChecker;
import com.feivirus.ruleengine.base.checker.DoubleConditionChecker;
import com.feivirus.ruleengine.base.checker.DoubleValueRangeConditionChecker;
import com.feivirus.ruleengine.base.checker.IntegerConditionChecker;
import com.feivirus.ruleengine.base.checker.IntegerValueRangeConditionChecker;
import com.feivirus.ruleengine.base.checker.StringConditionChecker;
import com.feivirus.ruleengine.base.checker.businesschecker.PersonGradeNumberRangeConditionChecker;
import com.feivirus.ruleengine.base.checker.businesschecker.PersonGradeNumberValueConditionChecker;
import com.feivirus.ruleengine.enums.ConditionValidatorType;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;
import com.feivirus.ruleengine.rule.dto.ValueRange;

/**
 * @author feivirus
 */
public class ConditionCheckerFactory {

    /**
     * 获取校验器
     *
     * @param checkerType 校验器类型
     * @param value       操作数
     * @param operation   操作符
     * @return
     */
    public static ConditionChecker createRuleCondition(ConditionValidatorType checkerType, Object value, OperatorEnum operation) {
        if (checkerType == null ||
                value == null) {
            return null;
        }
        // 范围比较操作数个数
        int valueRangeNum = 2;
        switch (checkerType) {
            case STRING_CHECKER:
                StringConditionChecker stringConditionChecker = new StringConditionChecker();
                if (value instanceof String) {
                    stringConditionChecker.setValue((String) value);
                } else if (value instanceof List) {
                    List<String> param = (List<String>) value;
                    StringBuffer buffer = new StringBuffer();
                    for (String pa : param) {
                        buffer.append(pa).append(",");
                    }
                    stringConditionChecker.setValue(buffer.toString());
                } else {
                    stringConditionChecker.setValue(String.valueOf(value));
                }
                return stringConditionChecker;
            case AREA_CODE_CHECKER:
                AreaConditionChecker areaConditionChecker = new AreaConditionChecker();
                if (value instanceof List) {
                    List<String> param = (List<String>) value;
                    areaConditionChecker.setSrcAreaCodes(param);
                }
                return areaConditionChecker;
            case INTEGER_CHECKER:
                IntegerConditionChecker integerConditionChecker = new IntegerConditionChecker();
                if (value instanceof Integer) {
                    integerConditionChecker.setValue((Integer) value);
                } else if (value instanceof String) {
                    integerConditionChecker.setValue(Integer.parseInt(value.toString()));
                }
                return integerConditionChecker;
            case DOUBLE_CHECKER:
                DoubleConditionChecker doubleConditionChecker = new DoubleConditionChecker();
                if (value instanceof Double) {
                    doubleConditionChecker.setValue((Double) value);
                } else if (value instanceof String) {
                    doubleConditionChecker.setValue(Double.parseDouble(value.toString()));
                }
                return doubleConditionChecker;
            case DOUBLE_VALUE_RANGE_CHECKER:
                ValueRange<Double, Double> doubleValueRange = doubleParamTransform(value, operation, valueRangeNum);
                DoubleValueRangeConditionChecker doubleValueRangeConditionChecker = new DoubleValueRangeConditionChecker(doubleValueRange.getStart(),
                        doubleValueRange.getEnd(), operation);
                return doubleValueRangeConditionChecker;
            case INTEGER_VALUE_RANGE_CHECKER:
                ValueRange<Integer, Integer> rangeList = null;
                if (value instanceof String) {
                    String[] param = value.toString().split(",");
                    if (valueRangeNum == param.length) {
                        rangeList = new ValueRange<>(Integer.parseInt(param[0]), Integer.parseInt(param[1]), operation);
                    }
                } else if (value instanceof ValueRange) {
                    rangeList = (ValueRange<Integer, Integer>) value;
                    rangeList.setOperation(operation);
                }
                IntegerValueRangeConditionChecker integerValueRangeConditionChecker = new IntegerValueRangeConditionChecker(rangeList.getStart(),
                        rangeList.getEnd(), operation);
                return integerValueRangeConditionChecker;
            case BOOLEAN_CHECKER:
                BooleanConditionChecker booleanConditionChecker = new BooleanConditionChecker();
                if (value instanceof Boolean) {
                    booleanConditionChecker.setValue((Boolean) value);
                } else if (value instanceof String) {
                    String val = value.toString();
                    if (Boolean.TRUE.toString().equals(val)) {
                        booleanConditionChecker.setValue(Boolean.TRUE);
                    } else if (Boolean.FALSE.toString().equals(val)) {
                        booleanConditionChecker.setValue(Boolean.FALSE);
                    }
                }
                return booleanConditionChecker;
            case DATE_VALUE_RANGE_CHECKER:
                ValueRange<Date, Date> dateValueRange = null;
                if (value instanceof String) {
                    String[] timeParams = (value.toString()).split(",");
                    if (valueRangeNum == timeParams.length) {
                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        try {
                            dateValueRange = new ValueRange<>(format.parse(timeParams[0]), format.parse(timeParams[1]), null);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                } else if (value instanceof List) {
                    List<Date> dateRange = (List<Date>) value;
                    dateValueRange = new ValueRange<>(dateRange.get(0), dateRange.get(1), null);
                } else if (value instanceof ValueRange) {
                    dateValueRange = (ValueRange<Date, Date>) value;
                }
                DateValueRangeConditionChecker dateValueRangeConditionChecker = new DateValueRangeConditionChecker(dateValueRange.getStart(),
                        dateValueRange.getEnd());
                return dateValueRangeConditionChecker;
            case PERSONGRADE_NUMBER_RANGE_CHECKER:
                ValueRange<Double, Double> personGradeValueRange = doubleParamTransform(value, operation, valueRangeNum);
                PersonGradeNumberRangeConditionChecker personGradeValueRangeConditionChecker = new PersonGradeNumberRangeConditionChecker(personGradeValueRange.getStart(),
                        personGradeValueRange.getEnd(), operation);
                return personGradeValueRangeConditionChecker;
            case PERSONGRADE_NUMBER_VALUE_CHECKER:
                PersonGradeNumberValueConditionChecker personGradeNumberValueConditionChecker = new PersonGradeNumberValueConditionChecker();
                if (value instanceof Double) {
                    personGradeNumberValueConditionChecker.setValue((Double) value);
                } else if (value instanceof String) {
                    personGradeNumberValueConditionChecker.setValue(Double.parseDouble(value.toString()));
                }
                return personGradeNumberValueConditionChecker;
            default:
        }

        return null;
    }

    private static ValueRange<Double, Double> doubleParamTransform(Object value, OperatorEnum operation, int valueRangeNum) {
        ValueRange<Double, Double> doubleValueRange = null;
        if (value instanceof String) {
            String[] param = value.toString().split(",");
            if (valueRangeNum == param.length) {
                doubleValueRange = new ValueRange<>(Double.parseDouble(param[0]), Double.parseDouble(param[1]), operation);
            }
        } else if (value instanceof ValueRange) {
            doubleValueRange = (ValueRange<Double, Double>) value;
            doubleValueRange.setOperation(operation);
        }
        return doubleValueRange;
    }

}
