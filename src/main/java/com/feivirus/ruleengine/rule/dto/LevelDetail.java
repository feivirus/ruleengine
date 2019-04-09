package com.feivirus.ruleengine.rule.dto;

import java.util.List;

/**
 * 
 * @author feivirus
 *
 */
public class LevelDetail {
	//适用团体: 代理公司
	private LevelGroup group;

	//二级渠道的帮派列表
	private List<LevelGroup> groupList;
	
	//商业险点数(%) 出单那个人,对应前端的三阶小飞侠
	private Double 	   point;
	
	//交强险点数
	private Double 	   forcePoint;
	
	//代理人类型, 1 不区分帮派身份 2 帮众 3 帮主
	private Integer     agentType;
	
	//指定人员：手机号 逗号分隔
	private String designatedPersons;
	
	//1 入帮推荐 2 帮主管理津贴
	private Integer     commissionType;
	
	//前端的第2阶小飞侠
	private Double 	   class2ndPoint;
	
	//前端的第2阶交强险
	private Double     class2ndForcePoint;

	private LevelGroup class3rdGroup;
	
	//前端的第1阶小飞侠
	private Double 	   class3rdPoint;

	//前端的第1阶交强险
	private Double     class3rdForcePoint;

	public LevelGroup getGroup() {
		return group;
	}

	public void setGroup(LevelGroup group) {
		this.group = group;
	}

	public List<LevelGroup> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<LevelGroup> groupList) {
		this.groupList = groupList;
	}

	public Double getPoint() {
		return point;
	}

	public void setPoint(Double point) {
		this.point = point;
	}

	public Double getForcePoint() {
		return forcePoint;
	}

	public void setForcePoint(Double forcePoint) {
		this.forcePoint = forcePoint;
	}

	public Integer getAgentType() {
		return agentType;
	}

	public void setAgentType(Integer agentType) {
		this.agentType = agentType;
	}

	public String getDesignatedPersons() {
		return designatedPersons;
	}

	public void setDesignatedPersons(String designatedPersons) {
		this.designatedPersons = designatedPersons;
	}

	public Integer getCommissionType() {
		return commissionType;
	}

	public void setCommissionType(Integer commissionType) {
		this.commissionType = commissionType;
	}

	public Double getClass2ndPoint() {
		return class2ndPoint;
	}

	public void setClass2ndPoint(Double class2ndPoint) {
		this.class2ndPoint = class2ndPoint;
	}

	public Double getClass2ndForcePoint() {
		return class2ndForcePoint;
	}

	public void setClass2ndForcePoint(Double class2ndForcePoint) {
		this.class2ndForcePoint = class2ndForcePoint;
	}

	public LevelGroup getClass3rdGroup() {
		return class3rdGroup;
	}

	public void setClass3rdGroup(LevelGroup class3rdGroup) {
		this.class3rdGroup = class3rdGroup;
	}

	public Double getClass3rdPoint() {
		return class3rdPoint;
	}

	public void setClass3rdPoint(Double class3rdPoint) {
		this.class3rdPoint = class3rdPoint;
	}

	public Double getClass3rdForcePoint() {
		return class3rdForcePoint;
	}

	public void setClass3rdForcePoint(Double class3rdForcePoint) {
		this.class3rdForcePoint = class3rdForcePoint;
	}
}
