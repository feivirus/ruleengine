package com.feivirus.ruleengine.enums;

import org.apache.commons.lang.StringUtils;

/**
 * code 取值于 rule_condition_term表中code
 */
public enum ConditionCodeEnum {
	RENEWAL_ENUM("renewalEnum", "新转续保"),
	BUSINESS_CLASS("businessClass", "太保商业险种类"),
	FORCE_CLASS("forceClass", "太保交强险种类"),
	INSURANCE_ASSOCIATION("insuranceAssociationList", "险种"),
	LEVEL3_BUSINESS("level3Business", "三级业务标识"),
	LEVEL4_BUSSINESS("level4BussinessList", "四级业务标识"),
	OWNER_GENDER("ownerGender", "车主性别"),
	OWNER_AGE("ownerAge", "车主年龄"),
	INSURED_AGE("insuredAge", "被保险人年龄"),
	INSURED_GENDER("insuredGender", "被保险人性别"),
	IS_TRANSFER("isTransfer", "是否过户车"),
	CAR_YEAR("carYear", "车龄"),
	USE_NATURE("useNatureList", "使用性质"),
	CAR_TYPE("carTypeList", "车辆种类"),
	PLATE_TYPE("plateTypeList", "号牌种类"),
	SEAT("seat", "座位数"),
	TONNAGE("tonnage", "吨位"),
	DIFF_CAR("diffCar", "是否异地车牌"),
	OUTINSURED_CAR("outInsuredCar", "是否脱保"),
	MODEL_RISK_CATEGORY("modelRiskCategory", "车型风险等级"),
	CAR_KIND_TYPE_LIST("carKindTypeList", "车辆种类"),
	FORCE_AND_BIS_TOTALEPR("forceAndBisTotalEPR", "太保交商合计预期赔付率"),
	NO_CLAIM_ADJUST_REASON("noClaimAdjustReason", "无赔优不浮动原因"),
	CHECK_RATIO_MULTIPLY_CHANNEL_RATIO("checkRatioMultiplyChannelRatio", "太保自主核保系数*自主渠道系数"),
	SRC_PRICE("srcPriceList", "新车购置价"),
	COUNTRY_NATURE("countryNature", "车型产地"),
	PERSON_GRADE("personGrade", "客户评分"),
	CAR_DAMAGE("carDamage", "车辆损失险"),
	THIRD_PARTY("thirdParty", "第三者责任险"),
	DRIVER_SEAT("driverSeat", "司机责任险"),
	PASSENGER_SEAT("passengerSeat", "乘客责任险"),
	THEFT("theft", "全车盗抢险"),
	GLASS("glass", "玻璃破碎险"),
	SCRATCH("scratch", "车身划痕险"),
	SELF_COMBUSTION("selfCombustion", "自燃损失险"),
	WATERLOGGED("waterlogged", "涉水险"),
	DESIGNATED_FACTORY("designatedFactory", "指定专修厂"),
	THIRD_PARTY_NOT_FOUND("thirdPartyNotFound", "车损无法找到第三方"),
	THIRD_PARTY_DOUBLE("thirdPartyDouble", "第三者险节假日限额翻倍"),
	ABSOLUTELY_DEDUCTIBLE_RATE("absolutelyDeductibleRate", "绝对免赔率特约"),
	DAMAGE_WHEEL_EXCEPT_SINGLE_SPECIAL_CLAUSE("damageWheelExceptSingleSpecialClause", "车轮单独损坏除外特约"),
	SPIRIT_DAMAGE_LIABILITY_COVERAGE("spiritDamageLiabilityCoverage", "精神损害抚慰金险"),
	IS_MORTGAGE_CAR("isMortgageCar", "是否按揭车"),
	IS_NEW_CAR("isNewCar", "是否新车"),
	CARS_RISK_CATEGORY("carsRiskCategory", "车系风险分类"),
	RISKS("risks", "险别"),
	IS_PLATFORM_RENEWAL("isPlatFormRenewal", "是否平台续保"),
	CLAIM_ADJUST_REASON("claimAdjustReason", "无赔优浮动原因"),
	INSURANCE_UNIQUE_FACTOR("InsuranceUniqueFactor", "太保风险类别"),
	BIS_RISKS_NUM("bisRisksNum", "商业险险别数量"),
	THIRD_INSURANCE_AMOUNT("thirdInsuranceAmount", "第三者责任险 保额"),
	DRIVER_INSURANCE_AMOUNT("driverInsuranceAmount", "司机责任险 保额"),
	PASSENGER_INSURANCE_AMOUNT("passengerInsuranceAmount", "乘客责任险 保额"),
	NICK_INSURANCE_AMOUNT("nickInsuranceAmount", "划痕损失险 保额"),
	EXPECTED_LOSS_RATIO("expectedLossRatio", "太保预期赔付率"),
	AUTONOMOUS_FACTOR("autonomousFactor", "自主系数"),
	CAR_BRAND("carbrand", "车型品牌"),
	CARS_NAME("carsName", "车系名称"),
	CAR_TYPE_RISK_LEVEL("carTypeRiskLevel", "太保车型风险等级"),
	CAR_DAMAGE_BJMP("carDamage_bjmp", "车辆损失险 不计免陪"),
	THIRD_PARTY_BJMP("thirdParty_bjmp", "第三者责任险 不计免陪"),
	DRIVER_SEAT_BJMP("driverSeat_bjmp", "司机责任险 不计免陪"),
	PASSENGER_SEAT_BJMP("passengerSeat_bjmp", "乘客责任险 不计免陪"),
	THEFT_BJMP("theft_bjmp", "全车盗抢险 不计免陪"),
	SCRATCH_BJMP("scratch_bjmp", "车身划痕险 不计免陪"),
	SELF_COMBUSTION_BJMP("selfCombustion_bjmp", "自燃损失险 不计免陪"),
	WATERLOGGED_BJMP("waterlogged_bjmp", "涉水险 不计免陪"),
	SPIRIT_DAMAGE_LIABILITY_COVERAGE_BJMP("spiritDamageLiabilityCoverage_bjmp", "精神损害抚慰金险 不计免陪"),
	SINGLE_THIRD_PARTY("singleThirdParty", "单三者"),
	;

	private String code;
	private String name;
	
	private ConditionCodeEnum() {
	}
	
	private ConditionCodeEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
	
	public ConditionCodeEnum getEnumByCode(String code) {
		if (StringUtils.isEmpty(code)) {
			return null;
		}
		for(ConditionCodeEnum enumItem : ConditionCodeEnum.values()) {
			if (enumItem.getCode().equals(code)) {
				return enumItem;
			}
		}
		return null;
	}
}
