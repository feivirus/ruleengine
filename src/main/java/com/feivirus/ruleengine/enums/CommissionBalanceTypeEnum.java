package com.feivirus.ruleengine.enums;

/**
 * 
 * @author feivirus
 *
 */
public enum CommissionBalanceTypeEnum {
		//净保费
		NET_PREMIUM(1),
		
		//非净保费
		NON_NET_PREMIUM(2);
		
		private int value;
		
		private CommissionBalanceTypeEnum(int value) {
			this.value = value;
		}
		
		public int value() {
			return value;
		}
		
		public static CommissionBalanceTypeEnum getBalanceType(Integer balanceType) {
			if (balanceType == null) {
				return null;
			}
			
			for (CommissionBalanceTypeEnum typeEnum : CommissionBalanceTypeEnum.values()) {
				if (typeEnum.value() == balanceType.intValue()) {
					return typeEnum;
				}
			}
			return null;
		}
}
