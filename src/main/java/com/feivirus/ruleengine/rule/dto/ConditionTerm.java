package com.feivirus.ruleengine.rule.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author feivirus
 *
 */
public class ConditionTerm implements Serializable{
	private static final long serialVersionUID = -7514058691953358054L;

	private String code;
	
	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
