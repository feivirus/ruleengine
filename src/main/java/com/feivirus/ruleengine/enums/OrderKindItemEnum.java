package com.feivirus.ruleengine.enums;

import com.ideacome.common.enums.InsuKindItem;

public enum OrderKindItemEnum {
    //insuRiskTerms
    CAR_DAMAGE(1, InsuKindItem.CAR_DAMAGE, ConditionCodeEnum.CAR_DAMAGE),

    //insuredAmount
    THIRD_PARTY(2, InsuKindItem.THIRD_PARTY, ConditionCodeEnum.THIRD_PARTY),
    DRIVER_SEAT(2, InsuKindItem.DRIVER_SEAT, ConditionCodeEnum.DRIVER_SEAT),
    PASSENGER_SEAT(2, InsuKindItem.PASSENGER_SEAT, ConditionCodeEnum.PASSENGER_SEAT),
    GLASS(2, InsuKindItem.GLASS, ConditionCodeEnum.GLASS),
    SCRATCH(2, InsuKindItem.SCRATCH, ConditionCodeEnum.SCRATCH),
    ABSOLUTELY_DEDUCTIBLE_RATE(2, InsuKindItem.ABSOLUTELY_DEDUCTIBLE_RATE, ConditionCodeEnum.ABSOLUTELY_DEDUCTIBLE_RATE),
    SPIRIT_DAMAGE_LIABILITY_COVERAGE(2, InsuKindItem.SPIRIT_DAMAGE_LIABILITY_COVERAGE, ConditionCodeEnum.SPIRIT_DAMAGE_LIABILITY_COVERAGE),
    //保额相关(KindRuleListConvertStrategy反转过滤)
    THIRD_INSURANCE_AMOUNT(2, InsuKindItem.THIRD_PARTY, ConditionCodeEnum.THIRD_INSURANCE_AMOUNT),
    DRIVER_INSURANCE_AMOUNT(2, InsuKindItem.DRIVER_SEAT, ConditionCodeEnum.DRIVER_INSURANCE_AMOUNT),
    PASSENGER_INSURANCE_AMOUNT(2, InsuKindItem.PASSENGER_SEAT, ConditionCodeEnum.PASSENGER_INSURANCE_AMOUNT),
    NICK_INSURANCE_AMOUNT(2, InsuKindItem.SCRATCH, ConditionCodeEnum.NICK_INSURANCE_AMOUNT),

    //isBuy
    CAR_DAMAGE_BJMP(3, InsuKindItem.CAR_DAMAGE_A, ConditionCodeEnum.CAR_DAMAGE_BJMP),
    THIRD_PARTY_BJMP(3, InsuKindItem.THIRD_PARTY_A, ConditionCodeEnum.THIRD_PARTY_BJMP),
    DRIVER_SEAT_BJMP(3, InsuKindItem.DRIVER_SEAT_A, ConditionCodeEnum.DRIVER_SEAT_BJMP),
    PASSENGER_SEAT_BJMP(3, InsuKindItem.PASSENGER_SEAT_A, ConditionCodeEnum.PASSENGER_SEAT_BJMP),
    SPIRIT_DAMAGE_LIABILITY_COVERAGE_BJMP(3, InsuKindItem.SPIRIT_DAMAGE_LIABILITY_COVERAGE_A, ConditionCodeEnum.SPIRIT_DAMAGE_LIABILITY_COVERAGE),
    SCRATCH_BJMP(3, InsuKindItem.SCRATCH_A, ConditionCodeEnum.SCRATCH_BJMP),
    THEFT_BJMP(3, InsuKindItem.THEFT_A, ConditionCodeEnum.THEFT_BJMP),
    SELF_COMBUSTION_BJMP(3, InsuKindItem.SELF_COMBUSTION_A, ConditionCodeEnum.SELF_COMBUSTION_BJMP),
    WATERLOGGED_BJMP(3, InsuKindItem.WATERLOGGED_A, ConditionCodeEnum.WATERLOGGED_BJMP),
    THEFT(3, InsuKindItem.THEFT, ConditionCodeEnum.THEFT),
    SELF_COMBUSTION(3, InsuKindItem.SELF_COMBUSTION, ConditionCodeEnum.SELF_COMBUSTION),
    WATERLOGGED(3, InsuKindItem.WATERLOGGED, ConditionCodeEnum.WATERLOGGED),
    DESIGNATED_FACTORY(3, InsuKindItem.DESIGNATED_FACTORY, ConditionCodeEnum.DESIGNATED_FACTORY),
    THIRD_PARTY_NOT_FOUND(3, InsuKindItem.THIRD_PARTY_NOT_FOUND, ConditionCodeEnum.THIRD_PARTY_NOT_FOUND),
    THIRD_PARTY_DOUBLE(3, InsuKindItem.THIRD_PARTY_DOUBLE, ConditionCodeEnum.THIRD_PARTY_DOUBLE),
    DAMAGE_WHEEL_EXCEPT_SINGLE_SPECIAL_CLAUSE(3, InsuKindItem.DAMAGE_WHEEL_EXCEPT_SINGLE_SPECIAL_CLAUSE, ConditionCodeEnum.DAMAGE_WHEEL_EXCEPT_SINGLE_SPECIAL_CLAUSE),

    ;

    private int type;

    private InsuKindItem insuKindItem;

    private ConditionCodeEnum conditionCodeEnum;

    OrderKindItemEnum(int type, InsuKindItem insuKindItem, ConditionCodeEnum conditionCodeEnum) {
        this.type = type;
        this.insuKindItem = insuKindItem;
        this.conditionCodeEnum = conditionCodeEnum;
    }

    public int getType() {
        return type;
    }

    public InsuKindItem getInsuKindItem() {
        return insuKindItem;
    }

    public ConditionCodeEnum getConditionCodeEnum() {
        return conditionCodeEnum;
    }
}
