package com.feivirus.ruleengine.base.instruction;

import com.feivirus.ruleengine.base.instruction.impl.DateEqualInstruction;
import com.feivirus.ruleengine.base.instruction.impl.DateGreaterEqualThanInstruction;
import com.feivirus.ruleengine.base.instruction.impl.DateGreaterThanInstruction;
import com.feivirus.ruleengine.base.instruction.impl.DateLessThanInstruction;
import com.feivirus.ruleengine.base.instruction.impl.DateNequalInstruction;
import com.feivirus.ruleengine.base.instruction.impl.NumberEqualInstruction;
import com.feivirus.ruleengine.base.instruction.impl.NumberGreaterEqualThanInstruction;
import com.feivirus.ruleengine.base.instruction.impl.NumberGreaterThanInstruction;
import com.feivirus.ruleengine.base.instruction.impl.NumberLessEqualInstruction;
import com.feivirus.ruleengine.base.instruction.impl.NumberLessThanInstruction;
import com.feivirus.ruleengine.base.instruction.impl.NumberNequalInstruction;
import com.feivirus.ruleengine.base.instruction.impl.StringEqualInstruction;
import com.feivirus.ruleengine.base.instruction.impl.StringInInstruction;
import com.feivirus.ruleengine.base.instruction.impl.StringNequalInstruction;
import com.feivirus.ruleengine.base.instruction.impl.ValueRangeNumberBetweenInstruction;
import com.feivirus.ruleengine.base.instruction.impl.ValueRangeNumberInInstruction;
import com.feivirus.ruleengine.base.instruction.operand.DoubleOperand;
import com.feivirus.ruleengine.base.instruction.operand.IntegerOperand;
import com.feivirus.ruleengine.base.instruction.operand.StringOperand;
import com.feivirus.ruleengine.base.instruction.operand.ValueRangeOperand;
import com.feivirus.ruleengine.enums.instruction.OperandEnum;
import com.feivirus.ruleengine.enums.instruction.OperatorEnum;

/**
 * 
 * @author feivirus
 *
 */
public class InstructionFactory {
	/**
	 * 本来想用泛型的，没想起来怎么转换.omg
	 * @param operatorEnum
	 * @return
	 */
	public static RelationalInstruction createInstruction(OperatorEnum operatorEnum, OperandEnum operandEnum) {
		if (operatorEnum == null) {
			return null;
		}
		//先以操作符,再以操作数区分
		if (operatorEnum == OperatorEnum.EQ) {
			
			if (operandEnum == OperandEnum.INTEGER) {
				return new NumberEqualInstruction<Integer, Integer>();
			} else if (operandEnum == OperandEnum.DOUBLE) {
				return new NumberEqualInstruction<Double, Double>();
			} else if (operandEnum == OperandEnum.DATE) {
				return new DateEqualInstruction();
			} else if (operandEnum == OperandEnum.STRING) {
				return new StringEqualInstruction();
			}			
		} else if (operatorEnum == OperatorEnum.NEQ) {

			if (operandEnum == OperandEnum.INTEGER) {
				return new NumberNequalInstruction<Integer, Integer>();
			} else if (operandEnum == OperandEnum.DOUBLE) {
				return new NumberNequalInstruction<Double, Double>();
			} else if (operandEnum == OperandEnum.DATE) {
				return new DateNequalInstruction();
			} else if (operandEnum == OperandEnum.STRING) {
				return new StringNequalInstruction();
			}
		}else if (operatorEnum == OperatorEnum.EGT) {
			
			if (operandEnum == OperandEnum.INTEGER) {
				return new NumberGreaterEqualThanInstruction<Integer, Integer>();
			} else if (operandEnum == OperandEnum.DOUBLE) {
				return new NumberGreaterEqualThanInstruction<Double, Double>();
			} else if (operandEnum == OperandEnum.DATE) {
				return new DateGreaterEqualThanInstruction();
			}			
		} else if (operatorEnum == OperatorEnum.GT) {
			
			if (operandEnum == OperandEnum.INTEGER) {
				return new NumberGreaterThanInstruction<Integer, Integer>();
			} else if (operandEnum == OperandEnum.DOUBLE) {
				return new NumberGreaterThanInstruction<Integer, Integer>();
			} else if (operandEnum == OperandEnum.DATE) {
				return new DateGreaterThanInstruction();
			}
		} else if (operatorEnum == OperatorEnum.LT) {
			
			if (operandEnum == OperandEnum.INTEGER) {
				return new NumberLessThanInstruction<Integer, Integer>();
			} else if (operandEnum == OperandEnum.DOUBLE) {
				return new NumberLessThanInstruction<Double, Double>();
			} else if (operandEnum == OperandEnum.DATE) {
				return new DateLessThanInstruction();
			}
		} else if (operatorEnum == OperatorEnum.ELT) {
			
			if (operandEnum == OperandEnum.INTEGER) {
				return new NumberLessEqualInstruction<Integer, Integer>();
			} else if (operandEnum == OperandEnum.DOUBLE) {
				return new NumberLessEqualInstruction<Double, Double>();
			}
		} else if (operatorEnum == OperatorEnum.EQ_BETWEEN ||
				operatorEnum == OperatorEnum.NOT_EQ_BETWEEN ||
				operatorEnum == OperatorEnum.LEFT_EQ_BETWEEN || 
				operatorEnum == OperatorEnum.RIGHT_EQ_BETWEEN) {
			
			if (operandEnum == OperandEnum.VALUE_RANCE_INTEGER) {
				return new ValueRangeNumberBetweenInstruction<ValueRangeOperand<IntegerOperand>, Integer>();
			} else if (operandEnum == OperandEnum.VALUE_RANGE_DOUBLE) {
				return new ValueRangeNumberBetweenInstruction<ValueRangeOperand<DoubleOperand>, Double>();
			}			
		} else if (operatorEnum == OperatorEnum.IN || 
				operatorEnum == OperatorEnum.NOT_IN) {
			
			if (operandEnum == OperandEnum.VALUE_RANCE_INTEGER) {
				return new ValueRangeNumberInInstruction<ValueRangeOperand<IntegerOperand>, Integer>();
			} else if (operandEnum == OperandEnum.VALUE_RANGE_DOUBLE) {
				return new ValueRangeNumberInInstruction<ValueRangeOperand<DoubleOperand>, Double>();
			} else if (operandEnum == OperandEnum.STRING) {
				return new StringInInstruction<ValueRangeOperand<StringOperand>>();
			}
		}
		return null;
	}
}
