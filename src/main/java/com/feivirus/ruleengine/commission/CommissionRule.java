package com.feivirus.ruleengine.commission;

import java.io.Serializable;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.feivirus.ruleengine.base.RuleMetaInfo;

@Document(collection = "commission_rule_v2")
public class CommissionRule implements MongoDBEntity<String>, Serializable {

	private static final long serialVersionUID = -1892523347594598597L;

	@Id
	private String id;

	private RuleMetaInfo ruleMetaInfo;

	//交易路由相关信息
	private RouteComputeRuleCondition routeComputeRuleCondition;

	// 应收条件
	private Map<String, RuleCondition> ruleConditionMap;
	
	//应付条件
	private PayableCondition payableCondition;

	@Override
	public String getUniqueId() {
		if(null!=ruleMetaInfo){
			return ruleMetaInfo.getRuleId();
		}else {
			return null;
		}
	}

	public RuleMetaInfo getRuleMetaInfo() {
		return ruleMetaInfo;
	}

	public void setRuleMetaInfo(RuleMetaInfo ruleMetaInfo) {
		this.ruleMetaInfo = ruleMetaInfo;
	}

	public RouteComputeRuleCondition getRouteComputeRuleCondition() {
		return routeComputeRuleCondition;
	}

	public void setRouteComputeRuleCondition(RouteComputeRuleCondition routeComputeRuleCondition) {
		this.routeComputeRuleCondition = routeComputeRuleCondition;
	}

	public Map<String, RuleCondition> getRuleConditionMap() {
		return ruleConditionMap;
	}

	public void setRuleConditionMap(Map<String, RuleCondition> ruleConditionMap) {
		this.ruleConditionMap = ruleConditionMap;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PayableCondition getPayableCondition() {
		return payableCondition;
	}

	public void setPayableCondition(PayableCondition payableCondition) {
		this.payableCondition = payableCondition;
	}
}
