package com.feivirus.ruleengine.base.checker;

import java.util.Date;

import com.feivirus.ruleengine.base.ConditionChecker;
import com.feivirus.ruleengine.enums.RelationEnum;
import com.feivirus.ruleengine.rule.dto.ValueRange;

/**
 * @author feivirus
 */
public class DateValueRangeConditionChecker extends ValueRange<Date, Date> implements ConditionChecker<ValueRange<Date, Date>> {

    public DateValueRangeConditionChecker(Date s, Date e) {
        // 时间范围的比较不需要操作符
        super(s, e, null);
    }

    @Override
    public RelationEnum compare(ValueRange<Date, Date> src) {
        if ((start == null) &&
                (end == null) &&
                (src.getStart() == null) &&
                (src.getEnd() == null)) {
            return RelationEnum.UNKNOWN;
        }
        if (start == null && end == null) {
            return RelationEnum.SUBSET;
        }
        if (src.getStart() == null &&
                src.getEnd() == null) {
            return RelationEnum.CONTAIN;
        }
        if (start.compareTo(src.getStart()) == 0 &&
                end.compareTo(src.getEnd()) == 0) {
            return RelationEnum.EQUAL;
        }
        //日期为a1 b1 a2 b2格式
        if (start.before(src.getStart()) && src.getStart().before(end) && end.before(src.getEnd())) {
            return RelationEnum.INTERSECT;
        }
        //日期为b1 a1 b2 a2格式
        if (src.getStart().before(start) && start.before(src.getEnd()) && src.getEnd().before(end)) {
            return RelationEnum.INTERSECT;
        }
        //日期为a1 a2 b1 b2格式
        if (end.before(src.getStart())) {
            return RelationEnum.SEPARATE;
        }
        //日期为b1 b2 a1 a2格式
        if (src.getEnd().before(start)) {
            return RelationEnum.SEPARATE;
        }
        //日期为a1 b1 b2 a2格式
        if (start.before(src.getStart()) && end.after(src.getEnd())) {
            return RelationEnum.CONTAIN;
        }
        //日期为a1 b1 b2=a2格式
        if (start.before(src.getStart()) && end.compareTo(src.getEnd()) == 0) {
            return RelationEnum.CONTAIN;
        }
        //日期为a1=b1 b2 a2格式
        if (start.compareTo(src.getStart()) == 0 && end.after(src.getEnd())) {
            return RelationEnum.CONTAIN;
        }
        //日期为b1 a1 a2 b2格式
        if (src.getStart().before(start) && src.getEnd().after(end)) {
            return RelationEnum.SUBSET;
        }
        //日期为b1=a1 a2 b2格式
        if (src.getStart().compareTo(start) == 0 && src.getEnd().after(end)) {
            return RelationEnum.SUBSET;
        }
        //日期为b1 a1 a2=b2格式
        if (src.getStart().before(start) && src.getEnd().compareTo(end) == 0) {
            return RelationEnum.SUBSET;
        }
        return RelationEnum.UNKNOWN;
    }
}
