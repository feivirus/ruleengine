package com.feivirus.ruleengine.rule;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.feivirus.ruleengine.base.RuleValidator;
import com.feivirus.ruleengine.commission.CommissionRule;
import com.feivirus.ruleengine.enums.ConditionValidatorType;
import com.feivirus.ruleengine.enums.RelationEnum;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;
import com.feivirus.ruleengine.rule.dto.ValueRange;

/**
 * @author mofeng
 * @description:
 * @date 2019/2/13
 */
public class DateValueRangeCheckerTest extends CommissionRuleTest {

    @Test
    public void equal() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String targetDate = "2019-09-28 00:00:00,2019-10-28 00:00:00";
        String[] timeParamsTarget = (targetDate.toString()).split(",");
        ValueRange<Date, Date> target = new ValueRange<>(format.parse(timeParamsTarget[0]), format.parse(timeParamsTarget[1]), null);

        String srcDate = "2019-09-28 00:00:00,2019-10-28 00:00:00";
        String[] timeParamsSrc = (srcDate.toString()).split(",");
        ValueRange<Date, Date> src = new ValueRange<>(format.parse(timeParamsSrc[0]), format.parse(timeParamsSrc[1]), null);
        setDateRangeParam(target, src, RelationEnum.EQUAL);
    }

    @Test
    public void separate() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String targetDate = "2018-09-28 00:00:00,2018-10-28 00:00:00";
        String[] timeParamsTarget = (targetDate.toString()).split(",");
        ValueRange<Date, Date> target = new ValueRange<>(format.parse(timeParamsTarget[0]), format.parse(timeParamsTarget[1]), null);

        String srcDate = "2019-09-28 00:00:00,2019-10-28 00:00:00";
        String[] timeParamsSrc = (srcDate.toString()).split(",");
        ValueRange<Date, Date> src = new ValueRange<>(format.parse(timeParamsSrc[0]), format.parse(timeParamsSrc[1]), null);
        setDateRangeParam(target, src, RelationEnum.SEPARATE);
    }

    @Test
    public void intersect() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String targetDate = "2018-09-28 00:00:00,2019-10-28 00:00:00";
        String[] timeParamsTarget = (targetDate.toString()).split(",");
        ValueRange<Date, Date> target = new ValueRange<>(format.parse(timeParamsTarget[0]), format.parse(timeParamsTarget[1]), null);

        String srcDate = "2019-09-28 00:00:00,2020-10-28 00:00:00";
        String[] timeParamsSrc = (srcDate.toString()).split(",");
        ValueRange<Date, Date> src = new ValueRange<>(format.parse(timeParamsSrc[0]), format.parse(timeParamsSrc[1]), null);
        setDateRangeParam(target, src, RelationEnum.INTERSECT);
    }

    @Test
    public void subset() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String targetDate = "2019-09-28 00:00:00,2019-10-28 00:00:00";
        String[] timeParamsTarget = (targetDate.toString()).split(",");
        ValueRange<Date, Date> target = new ValueRange<>(format.parse(timeParamsTarget[0]), format.parse(timeParamsTarget[1]), null);

        String srcDate = "2019-06-28 00:00:00,2019-12-28 00:00:00";
        String[] timeParamsSrc = (srcDate.toString()).split(",");
        ValueRange<Date, Date> src = new ValueRange<>(format.parse(timeParamsSrc[0]), format.parse(timeParamsSrc[1]), null);
        setDateRangeParam(target, src, RelationEnum.SUBSET);
    }

    @Test
    public void contain() throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String targetDate = "2019-06-28 00:00:00,2019-12-28 00:00:00";
        String[] timeParamsTarget = (targetDate.toString()).split(",");
        ValueRange<Date, Date> target = new ValueRange<>(format.parse(timeParamsTarget[0]), format.parse(timeParamsTarget[1]), null);

        String srcDate = "2019-09-28 00:00:00,2019-10-28 00:00:00";
        String[] timeParamsSrc = (srcDate.toString()).split(",");
        ValueRange<Date, Date> src = new ValueRange<>(format.parse(timeParamsSrc[0]), format.parse(timeParamsSrc[1]), null);
        setDateRangeParam(target, src, RelationEnum.CONTAIN);
    }


    private void setDateRangeParam(ValueRange<Date, Date> target, ValueRange<Date, Date> src, RelationEnum relationEnum) {
        String termCode = "forceClass";
        CommissionRule ruleSrc = buildCommissionRule(src, OperatorEnum.IN, termCode, ConditionValidatorType.DATE_VALUE_RANGE_CHECKER);
        CommissionRule ruleTarget = buildCommissionRule(target, OperatorEnum.IN, termCode, ConditionValidatorType.DATE_VALUE_RANGE_CHECKER);
        RelationEnum result = new RuleValidator().validateConflict(ruleTarget,ruleSrc);
        Assert.assertTrue(result.equals(relationEnum));
    }

}
