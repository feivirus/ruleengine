package com.feivirus.ruleengine.enums;

public enum SingleThirdPartyEnum implements BaseEnum{
    singleThird("singleThirdParty", "单三者"),
    noSingleThird("notSingleThirdParty", "非单三者"),
    ;

    private String code;
    private String name;

    SingleThirdPartyEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }


    @Override
    public String getKey() {
        return code;
    }

    @Override
    public String getValue() {
        return name;
    }
}
