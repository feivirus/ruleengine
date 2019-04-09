package com.feivirus.ruleengine.commission;

import java.io.Serializable;

import com.feivirus.ruleengine.base.Validatable;
import com.feivirus.ruleengine.enums.ConditionValidatorType;

/**
 * @author feivirus
 *         规则包含各种规则条件,规则条件由规则术语+指令组成，指令由操作符+操作数组成.
 *         规则入库前需要针对术语+支持的指令部分一起通过校验器做冲突检测,
 */
public class RuleCondition implements Serializable, Validatable {

    private static final long serialVersionUID = -7591663958446400103L;

    //比如srcPriceList
    private String termCode;

    //比如新车购置价
    private String termName;

    //@link CommissionRuleTermCategoryEnum
    private String category;

    //对应数据库中的supported_operation
    private String supportedOperation;

    //用户选择的操作符对应数据库中的operation_one,比如between,@link OperatorEnum
    private String operation;

    //操作数, 比如30,20.
    private Object operand;

    //操作数类型@link InputTypeEnum
    private String inputType;

    //操作数类型,@link OperandEnum
    private Integer operandType;

    // 列表多选时前端显示用，只有operand中的code
    private String operandVO;

    //比如 车龄: 范围是 (1,3)
    private String description;

    //冲突检测的校验器类型, @link ConditionValidatorType
    private Integer checkerType;

    // 前段使用后端不需要关注
    private Double key;

    // 维度单位
    private String unit;
    // 维度单位Code
    private String unitCode;

    public RuleCondition() {
    }

    public RuleCondition(String termCode, String termName, String category,
                         String operation, Object operand, String inputType,
                         Integer operandType, String operandVO, String description,
                         Integer checkerType, Double key) {
        this.termCode = termCode;
        this.termName = termName;
        this.category = category;
        this.operation = operation;
        this.operand = operand;
        this.inputType = inputType;
        this.operandType = operandType;
        this.operandVO = operandVO;
        this.description = description;
        this.checkerType = checkerType;
        this.key = key;
    }

    public String getTermCode() {
        return termCode;
    }

    public void setTermCode(String termCode) {
        this.termCode = termCode;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSupportedOperation() {
        return supportedOperation;
    }

    public void setSupportedOperation(String supportedOperation) {
        this.supportedOperation = supportedOperation;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Object getOperand() {
        return operand;
    }

    public void setOperand(Object operand) {
        this.operand = operand;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getOperandVO() {
        return operandVO;
    }

    public void setOperandVO(String operandVO) {
        this.operandVO = operandVO;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCheckerType() {
        return checkerType;
    }

    public void setCheckerType(Integer checkerType) {
        this.checkerType = checkerType;
    }

    public Integer getOperandType() {
        return operandType;
    }

    public void setOperandType(Integer operandType) {
        this.operandType = operandType;
    }

    public Double getKey() {
        return key;
    }

    public void setKey(Double key) {
        this.key = key;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    @Override
    public ConditionValidatorType validateType() {
        return ConditionValidatorType.getConditionCheckerEnum(checkerType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;    //能调用这个方法，this肯定不为null，所以不判断this
        if (this.getClass() != obj.getClass()) return false; //如果不死同一个类，则必然false
        RuleCondition that = (RuleCondition) obj; //将Object类型的x转换为RuleCondition型。因为上一行已经判断了x是否为RuleCondition型，所以可以直接转换

        if (unEquals(this.termCode, that.termCode)) return false;
        if (unEquals(this.termName, that.termName)) return false;
        if (unEquals(this.category, that.category)) return false;
        if (unEquals(this.operation, that.operation)) return false;
        if (unEquals(this.operand, that.operand)) return false;
        if (unEquals(this.operandType, that.operandType)) return false;
        if (unEquals(this.operandVO, that.operandVO)) return false;
        //前端展示字段，不比较
        //if(unEquals(this.description, that.description)) return false;
        if (unEquals(this.checkerType, that.checkerType)) return false;
        //key前端属性，不比较
        //if(unEquals(this.key, that.key)) return false;

        return true;
    }

    private boolean equals(Object o1, Object o2) {
        if (o1 == null && o2 == null) {
            return true;
        }
        if (o1 != null && o1.equals(o2)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean unEquals(Object o1, Object o2) {
        return !equals(o1, o2);
    }
}
