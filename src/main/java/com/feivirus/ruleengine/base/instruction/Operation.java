package com.feivirus.ruleengine.base.instruction;

public interface Operation {

    void replace(Object oldValue, Object newValue);

    //参考RuleUnitCovertEnum.getMultiple中倍数处理
    default void multiply(Integer multiple){

    }

}
