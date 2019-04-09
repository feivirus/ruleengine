package com.feivirus.ruleengine.base.instruction;

import java.util.List;

import com.feivirus.ruleengine.base.instruction.operand.ValueRangeOperand;
import com.feivirus.ruleengine.enums.RelationEnum;
import com.feivirus.ruleengine.enums.instruction.OperandEnum;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

/**
 * 
 * @author feivirus
 *
 * @param <T>
 * 
 * 表达式由操作符+操作数+操作指令组成.
 * 目前只支持返回布尔类型的指令运算，不支持加减乘除的返回数据类型相同的运算
 */
public class ExpressionManager<T>{
	private static final long serialVersionUID = 2460398928433780544L;
	
	//操作符
	private OperatorEnum operatorEnum;
	
	//条件内的目标操作数
	private Operand<T> sourceOperand;		

	public ExpressionManager(OperatorEnum operatorEnum, Operand<T> sourceOperand) {
		super();
		this.operatorEnum = operatorEnum;
		this.sourceOperand = sourceOperand;
	}

	public OperatorEnum getOperatorEnum() {
		return operatorEnum;
	}

	public void setOperatorEnum(OperatorEnum operatorEnum) {
		this.operatorEnum = operatorEnum;
	}

	public Operand<T> getSourceOperand() {
		return sourceOperand;
	}

	public void setSourceOperand(Operand<T> sourceOperand) {
		this.sourceOperand = sourceOperand;
	}

	public boolean match(T targetOperand, OperandExtend operandExtend) {
		//操作数为空，则默认匹配不中
		if(targetOperand == null){
			return false;
		}
		RelationalInstruction<T, T> instruction = InstructionFactory.createInstruction(operatorEnum,
				sourceOperand.type());
		boolean ret = false;
		
		if (instruction != null) {
			InstructionDecorator instructionDecorator = InstructionDecoratorFactory.createInstructionDecorator(instruction, operandExtend);
			ret =  instructionDecorator.match(sourceOperand.value(), targetOperand, operatorEnum);
		}
		return ret;
	}

	/**
	 * 反向匹配 （list对比ValueRangeOperand）
	 * 常规操作数，不兼容区间操作数
	 * @param targetOperandList
	 * @param operandExtend
	 * @return
	 */
	public boolean reverseMatch(List<T> targetOperandList, OperandExtend operandExtend){
		//操作数为空，则默认匹配中
		if(targetOperandList == null){
			return false;
		}
		int count = 0;
		for(T targetOperand : targetOperandList){
			if(match(targetOperand, operandExtend)){
				count++;
			}
		}
		//sourceOperand全部在targetOperandList中
		if(count == ((ValueRangeOperand)sourceOperand.value()).getOperandList().size()){
			return true;
		}
		return false;
	}
}
