package com.feivirus.ruleengine.rule.dto;

import java.util.Date;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.annotation.Transient;

/**
 * 
 * @author feivirus
 *
 */
public class SceneDetail {
	private String id;
	
	//具体整数值见AppMark枚举类, 1喂小保 2小飞侠
	private Integer appMark;
	
	//1级渠道
    private LevelDetail level1; 
    
    private LevelDetail level2;
    
    private LevelDetail level3;

	//规则状态, @link RuleStatusEnum
    private Integer status;     
    
    private String  endReason; 
    
    private String  endTime; 
    
    //创建人
  	private Integer createUser;
  	
  	@Transient
  	private String createUserName;
  	
  	//创建时间
  	private Date createTime;
  	
  	//终止人
  	private Integer endUser;
  	
  	@Transient
  	private String endUserName;
  	
  	//修改人
  	private Integer modifyUser;
  	
  	@Transient
  	private String modifyUserName;
  	
  	//修改时间
  	private Date modifyTime;

	//生效时间
  	private Date effectiveDateStr;
  	
  	//失效时间
	private Date expirationDateStr;
	
	//失效操作的人
	private String expirationUser;
	
	//失效操作的原因
	private String expirationReason;
	
    //失效操作的时间
	private Date expirationTime;
	
	//修改有效期规则的人
	private String updateTimeUser;
	
	//修改有效期规则的原因
	private String updateTimeReason;
	
	//修改有效期规则的时间
	private Date updateTimeDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getAppMark() {
		return appMark;
	}

	public void setAppMark(Integer appMark) {
		this.appMark = appMark;
	}

	public LevelDetail getLevel1() {
		return level1;
	}

	public void setLevel1(LevelDetail level1) {
		this.level1 = level1;
	}

	public LevelDetail getLevel2() {
		return level2;
	}

	public void setLevel2(LevelDetail level2) {
		this.level2 = level2;
	}

	public LevelDetail getLevel3() {
		return level3;
	}

	public void setLevel3(LevelDetail level3) {
		this.level3 = level3;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEndReason() {
		return endReason;
	}

	public void setEndReason(String endReason) {
		this.endReason = endReason;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getEndUser() {
		return endUser;
	}

	public void setEndUser(Integer endUser) {
		this.endUser = endUser;
	}

	public String getEndUserName() {
		return endUserName;
	}

	public void setEndUserName(String endUserName) {
		this.endUserName = endUserName;
	}

	public Integer getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(Integer modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getModifyUserName() {
		return modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getEffectiveDateStr() {
		return effectiveDateStr;
	}

	public void setEffectiveDateStr(Date effectiveDateStr) {
		this.effectiveDateStr = effectiveDateStr;
	}

	public Date getExpirationDateStr() {
		return expirationDateStr;
	}

	public void setExpirationDateStr(Date expirationDateStr) {
		this.expirationDateStr = expirationDateStr;
	}

	public String getExpirationUser() {
		return expirationUser;
	}

	public void setExpirationUser(String expirationUser) {
		this.expirationUser = expirationUser;
	}

	public String getExpirationReason() {
		return expirationReason;
	}

	public void setExpirationReason(String expirationReason) {
		this.expirationReason = expirationReason;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String getUpdateTimeUser() {
		return updateTimeUser;
	}

	public void setUpdateTimeUser(String updateTimeUser) {
		this.updateTimeUser = updateTimeUser;
	}

	public String getUpdateTimeReason() {
		return updateTimeReason;
	}

	public void setUpdateTimeReason(String updateTimeReason) {
		this.updateTimeReason = updateTimeReason;
	}

	public Date getUpdateTimeDate() {
		return updateTimeDate;
	}

	public void setUpdateTimeDate(Date updateTimeDate) {
		this.updateTimeDate = updateTimeDate;
	}
	
	public boolean biggerThen(SceneDetail obj) {
		if (obj == null) {
			return true;
		}
		int thisLevel = this.getSceneDetailLevel();
		int objLevel = obj.getSceneDetailLevel();
		if(thisLevel > objLevel){
			return true;
		}else if(thisLevel < objLevel){
			return false;
		}
		//level相等场景，比较点数
		LevelDetail thisLevel3 = this.getLevel3();
		LevelDetail compareLevel3 = obj.getLevel3();
		if (compareLevel3 == null || compareLevel3.getPoint() == null) {
			return true;
		}
		if (thisLevel3 == null || thisLevel3.getPoint() == null) {
			return false;
		}
		// 先看直接点数
		if (!thisLevel3.getPoint().equals(compareLevel3.getPoint())) {
			return thisLevel3.getPoint().longValue() > compareLevel3.getPoint().longValue();
		}
		// 相等下判断间接点数
		if (compareLevel3.getClass2ndPoint() == null) {
			return true;
		}
		if (thisLevel3.getClass2ndPoint() == null) {
			return false;
		}
		if (!thisLevel3.getClass2ndPoint().equals(compareLevel3.getClass2ndPoint())) {
			return thisLevel3.getClass2ndPoint().longValue() > compareLevel3.getClass2ndPoint().longValue();
		}
		// 最后比较间接的间接点数
		if (compareLevel3.getClass3rdPoint() == null) {
			return true;
		}
		if (thisLevel3.getClass3rdPoint() == null) {
			return false;
		}
		return thisLevel3.getClass3rdPoint().longValue() >= compareLevel3.getClass3rdPoint().longValue();
	}

	/**
	 * 是否是全部
	 * 默认全部优先级最低，有问题的都归类为全部
	 *
	 * @return
	 */
	public boolean defaultAll() {
		if (this.getLevel2() == null) {
			return true;
		}
		LevelGroup allGroup = new LevelGroup(0);
		List<LevelGroup> groupList = this.getLevel2().getGroupList();
		if (CollectionUtils.isEmpty(groupList)) {
			return true;
		}
		return groupList.contains(allGroup);
	}

	/**
	 * 判断是否是指定手机号
	 *
	 * @return
	 */
	public boolean designatedMobile() {
		if (this.getLevel3() == null) {
			return false;
		}
		Integer agentType = this.getLevel3().getAgentType();
		if (agentType == null || !agentType.equals(AgentTypeEnum.MEMBER_WITH_MOBILES.getValue())) {
			return false;
		}
		String designatedPersons = this.getLevel3().getDesignatedPersons();
		if (StringUtils.isBlank(designatedPersons)) {
			return false;
		}
		return true;
	}

	public int getSceneDetailLevel(){
		if(designatedMobile()){
			return 3;
		}else if(defaultAll()){
			return 1;
		}else{
			return 2;
		}
	}

}
