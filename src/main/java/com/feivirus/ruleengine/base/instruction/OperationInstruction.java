package com.feivirus.ruleengine.base.instruction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.feivirus.ruleengine.commission.RuleCondition;
import com.feivirus.ruleengine.enums.ConditionCodeEnum;


/**
 * 特殊值转化
 * 险企特有因子中空格会被前端处理掉，所以定义为"space"，后端再处理回空格
 */
public class OperationInstruction {

    private static String INSURANCE_UNIQUE_FACTOR_SPACE = "space";

    private static String INSURANCE_UNIQUE_FACTOR_SPACE_VALUE = " ";

    private static List<String> needToDealUnitList = new ArrayList<String>(){
        {
            this.add(ConditionCodeEnum.THIRD_INSURANCE_AMOUNT.getCode());
            this.add(ConditionCodeEnum.DRIVER_INSURANCE_AMOUNT.getCode());
            this.add(ConditionCodeEnum.PASSENGER_INSURANCE_AMOUNT.getCode());
            this.add(ConditionCodeEnum.NICK_INSURANCE_AMOUNT.getCode());
        }
    };

    public static void doSpecialTransformation(RuleCondition ruleCondition, Operand<Object> srcOperand){
        //险企特有因子中space需要转化成空格
        if(ConditionCodeEnum.INSURANCE_UNIQUE_FACTOR.getCode().equals(ruleCondition.getTermCode())){
            if(srcOperand instanceof Operation){
                ((Operation)srcOperand).replace(INSURANCE_UNIQUE_FACTOR_SPACE, INSURANCE_UNIQUE_FACTOR_SPACE_VALUE);
            }
        }
        doUnitTransformation(ruleCondition, srcOperand);
    }

    //规则配置保额单位是元，匹配时目前是分
    public static void doUnitTransformation(RuleCondition ruleCondition, Operand<Object> srcOperand){
        if(needToDealUnitList.contains(ruleCondition.getTermCode())){
            //转化为分，后续以分比较
            Integer multiple = RuleUnitCovertEnum.getMultiple(RuleUnitEnum.getRuleUnitEnum(ruleCondition.getUnitCode()), RuleUnitEnum.FEN);
            if(multiple == null || multiple == 1){
                return;
            }
            //转化操作数
            if(srcOperand instanceof Operation){
                ((Operation)srcOperand).multiply(multiple);
            }
        }
    }

    public static Object doSpecialTransformation(String termCode, Object operand){
        //险企特有因子中space需要转化成空格
        if(ConditionCodeEnum.INSURANCE_UNIQUE_FACTOR.getCode().equals(termCode)){
            if(Objects.equals(INSURANCE_UNIQUE_FACTOR_SPACE, operand)){
                return INSURANCE_UNIQUE_FACTOR_SPACE_VALUE;
            }
        }
        return operand;
    }

}
