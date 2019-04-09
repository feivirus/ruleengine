package com.feivirus.ruleengine.rule.dto;

import com.feivirus.ruleengine.enums.RelationEnum;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

/**
 * @param <S>
 * @param <E>
 * @author feivirus
 */
public class ValueRange<S, E> extends RangeOperation {
    protected S start;

    protected E end;

    public ValueRange(S s, E e, OperatorEnum operator) {
        super(operator);
        this.start = s;
        this.end = e;
    }

    public S getStart() {
        return start;
    }

    public void setStart(S start) {
        this.start = start;
    }

    public E getEnd() {
        return end;
    }

    public void setEnd(E end) {
        this.end = end;
    }

    public <T extends Number> RelationEnum compare(ValueRange<T, T> src, ValueRange<T, T> target) {
        if (src == null && target == null) {
            return RelationEnum.UNKNOWN;
        }
        if (src == null) {
            return RelationEnum.CONTAIN;
        }
        if (src.getStart() == null && src.getEnd() == null) {
            return RelationEnum.CONTAIN;
        }
        if (target == null) {
            return RelationEnum.SUBSET;
        }
        if (target.getStart() == null && target.getEnd() == null) {
            return RelationEnum.SUBSET;
        }

        double srcStart = src.getStart().doubleValue();
        double srcEnd = src.getEnd().doubleValue();
        // 原数据的范围操作符
        OperatorEnum srcOperator = src.getOperation();
        double targetStart = target.getStart().doubleValue();
        double targetEnd = target.getEnd().doubleValue();
        // 目标数据的范围操作符
        OperatorEnum targetOperator = target.getOperation();

        /**
         * 为了便于理解 原操作数表示为a1,a2 目标操作数表示为b1,b2
         */

        // 相离关系 a2 < b1 || b2 < a1
        if (srcStart > targetEnd || srcEnd < targetStart) {
            return RelationEnum.SEPARATE;
        }

        // 子集关系 a1 < b1 && b2 < a2
        if (srcStart < targetStart && targetEnd < srcEnd) {
            return RelationEnum.SUBSET;
        }

        // 父集(包含)关系 b1 < a1 && a2 < b2
        if (targetStart < srcStart && srcEnd < targetEnd) {
            return RelationEnum.CONTAIN;
        }

        // 相等关系 a1 == b1 && a2 == b2
        if (srcStart == targetStart && srcEnd == targetEnd) {
            // 操作符相等
            if (targetOperator == srcOperator) {
                return RelationEnum.EQUAL;
            } else {
                if (srcOperator == OperatorEnum.EQ_BETWEEN && (targetOperator == OperatorEnum.RIGHT_EQ_BETWEEN
                        || targetOperator == OperatorEnum.LEFT_EQ_BETWEEN || targetOperator == OperatorEnum.NOT_EQ_BETWEEN)) {
                    return RelationEnum.SUBSET;
                } else if (targetOperator == OperatorEnum.EQ_BETWEEN && (srcOperator == OperatorEnum.RIGHT_EQ_BETWEEN
                        || srcOperator == OperatorEnum.LEFT_EQ_BETWEEN || srcOperator == OperatorEnum.NOT_EQ_BETWEEN)) {
                    return RelationEnum.CONTAIN;
                } else if ((srcOperator == OperatorEnum.RIGHT_EQ_BETWEEN && targetOperator == OperatorEnum.LEFT_EQ_BETWEEN)
                        || (targetOperator == OperatorEnum.RIGHT_EQ_BETWEEN && srcOperator == OperatorEnum.LEFT_EQ_BETWEEN)) {
                    return RelationEnum.INTERSECT;
                } else if ((srcOperator == OperatorEnum.RIGHT_EQ_BETWEEN && targetOperator == OperatorEnum.NOT_EQ_BETWEEN)
                        || (targetOperator == OperatorEnum.RIGHT_EQ_BETWEEN && srcOperator == OperatorEnum.NOT_EQ_BETWEEN)) {
                    return RelationEnum.CONTAIN;
                } else if ((srcOperator == OperatorEnum.LEFT_EQ_BETWEEN && targetOperator == OperatorEnum.NOT_EQ_BETWEEN)
                        || (targetOperator == OperatorEnum.LEFT_EQ_BETWEEN && srcOperator == OperatorEnum.NOT_EQ_BETWEEN)) {
                    return RelationEnum.CONTAIN;
                }
            }
        }

        /** 相交关系 */
        //a1 b1 a2 b2格式
        if (srcStart < targetStart && targetStart <= srcEnd && srcEnd < targetEnd) {
            if (targetStart == srcEnd) {
                return intersectRelation(srcOperator, targetOperator);
            } else {
                return RelationEnum.INTERSECT;
            }
        }

        //b1 a1 b2 a2格式
        if (targetStart < srcStart && srcStart <= targetEnd && targetEnd < srcEnd) {
            if (srcStart == targetEnd) {
                return intersectRelation(targetOperator, srcOperator);
            } else {
                return RelationEnum.INTERSECT;
            }
        }

        return RelationEnum.UNKNOWN;
    }

    /**
     * 相交关系边界值判断
     *
     * @param srcOperator
     * @param targetOperator
     * @return
     */
    private RelationEnum intersectRelation(OperatorEnum srcOperator, OperatorEnum targetOperator) {
        //  目标 全闭 原 全闭
        if (targetOperator == OperatorEnum.EQ_BETWEEN && srcOperator == OperatorEnum.EQ_BETWEEN) {
            return RelationEnum.INTERSECT;
            // 目标 全闭 原 左闭右开
        } else if (targetOperator == OperatorEnum.EQ_BETWEEN && srcOperator == OperatorEnum.LEFT_EQ_BETWEEN) {
            return RelationEnum.INTERSECT;
            // 目标 左开右闭 原 全闭
        } else if (targetOperator == OperatorEnum.RIGHT_EQ_BETWEEN && srcOperator == OperatorEnum.EQ_BETWEEN) {
            return RelationEnum.INTERSECT;
            // 目标 左开右闭 原 左闭右开
        } else if (targetOperator == OperatorEnum.RIGHT_EQ_BETWEEN && srcOperator == OperatorEnum.LEFT_EQ_BETWEEN) {
            return RelationEnum.INTERSECT;
        } else {
            return RelationEnum.SEPARATE;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((end == null) ? 0 : end.hashCode());
        result = prime * result + ((start == null) ? 0 : start.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ValueRange other = (ValueRange) obj;
        if (end == null) {
            if (other.end != null)
                return false;
        } else if (!end.equals(other.end))
            return false;
        if (start == null) {
            if (other.start != null)
                return false;
        } else if (!start.equals(other.start))
            return false;
        return true;
    }
}
