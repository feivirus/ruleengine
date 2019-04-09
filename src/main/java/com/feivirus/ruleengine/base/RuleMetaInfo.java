package com.feivirus.ruleengine.base;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

/**
 * 
 * @author feivirus
 * 规则元数据信息
 */
public class RuleMetaInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2075789937726324316L;

	//单证的规则，佣金的规则等
	private Integer type;
	
	//规则id
	private String   ruleId;
	
	//规则状态, @link RuleStatusEnum
	private Integer status;
	
	//规则版本
	private Double version;
	
	//创建时间
	private Date addTime;
	
	//创建人
	private Integer addUser;
	
	//修改时间，如果是结束操作，则为结束操作时间
	private Date lastModifyTime;
	
	//修改人,如果是结束操作，则为结束操作人
	private Integer lastModifyUser;
	
	//修改原因，如果是结束操作，则为结束操作原因
	private String lastMofidyReason;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getVersion() {
		return version;
	}

	public void setVersion(Double version) {
		this.version = version;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getAddUser() {
		return addUser;
	}

	public void setAddUser(Integer addUser) {
		this.addUser = addUser;
	}

	public Date getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public Integer getLastModifyUser() {
		return lastModifyUser;
	}

	public void setLastModifyUser(Integer lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	public String getLastMofidyReason() {
		return lastMofidyReason;
	}

	public void setLastMofidyReason(String lastMofidyReason) {
		this.lastMofidyReason = lastMofidyReason;
	}		
}
