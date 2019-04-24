package com.feivirus.ruleengine.commission;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Collectors;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.alibaba.fastjson.JSONObject;


public class CommissionRuleDAO extends GenericMongoDAO<CommissionRule, String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommissionRuleDAO.class);

    public CommissionRuleDAO(MongoOperations mongoOpers) {
        super(mongoOpers);
    }

    public long selectRuleListCount(Map<String, Object> params) {
        Query query = ruleGeneralQuery(params);
        LOGGER.info("查询规则总条数入参，{}", JSONObject.toJSONString(query));
        return mongoOpers.count(query, CommissionRule.class);
    }

    public CommissionRule selectRuleBySceneDetailId(String sceneDetailId) {
        if (StringUtils.isBlank(sceneDetailId)) {
            return null;
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("payableCondition.sceneList").elemMatch(Criteria.where("_id").is(sceneDetailId)));
        CommissionRule commissionRule = mongoOpers.findOne(query, CommissionRule.class);
        return commissionRule;
    }

    public List<CommissionRule> selectRuleByContractNo(String contractNo) {
        if (StringUtils.isBlank(contractNo)) {
            return null;
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("routeComputeRuleCondition.contractNo").is(contractNo));
        List<Integer> status = new ArrayList<>();
        status.add(RuleStatusEnum.RULE_STATUS_ONGOING.value());
        status.add(RuleStatusEnum.RULE_STATUS_TIME_NOT_COME.value());
        query.addCriteria(Criteria.where("ruleMetaInfo.status").in(status));
        List<CommissionRule> commissionRule = mongoOpers.find(query, CommissionRule.class);
        return commissionRule;
    }

    public CommissionRule selectRuleByRuleId(String ruleId) {
        if (StringUtils.isBlank(ruleId)) {
            return null;
        }
        Criteria ruleIdCriteria = Criteria.where("ruleMetaInfo.ruleId").is(ruleId);
        Criteria idCriteria = Criteria.where("_id").is(ruleId);
        Criteria tmpCriteria = new Criteria();

        Query query = new Query(tmpCriteria.orOperator(idCriteria, ruleIdCriteria));
        CommissionRule commissionRule = mongoOpers.findOne(query, CommissionRule.class);
        return commissionRule;
    }

    /**
     * 根据帮派id查询相关文档
     *
     * @param groupId
     */
    public List<CommissionRule> selectRuleByGroupId(Integer groupId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("payableCondition.sceneList.appMark").is(2));
        query.addCriteria(Criteria.where("payableCondition.sceneList.level2.groupList.id").is(groupId));
        return mongoOpers.find(query, CommissionRule.class);
    }

    /**
     * 物理删除,删除后重新生成（初始化使用）
     *
     * @param ruleId
     * @return
     */
    public CommissionRule deleteRuleByRuleId(String ruleId) {
        if (StringUtils.isBlank(ruleId)) {
            return null;
        }
        Query query = new Query();
        query.addCriteria(Criteria.where("ruleMetaInfo.ruleId").is(ruleId));
        CommissionRule commissionRule = mongoOpers.findAndRemove(query, CommissionRule.class);
        return commissionRule;
    }

    public List<CommissionRule> selectRuleList(Map<String, Object> params) {
        Query query = ruleGeneralQuery(params);
        if (params.get("ingorePage") == null) {
            query.skip(Integer.parseInt(String.valueOf(params.get("pageOffset"))));
            query.limit(Integer.parseInt(String.valueOf(params.get("pageSize"))));
        }
        // 应收规则排序查询
        Sort.Order effectiveOrder = null;
        Sort.Order expirationOrder = null;
        if (null != params.get("effectiveOrderBy")) {
            Sort.Direction direction = "DESC".equals(params.get("effectiveOrderBy")) ? Sort.Direction.DESC : Sort.Direction.ASC;
            effectiveOrder = new Sort.Order(direction, "routeComputeRuleCondition.effectiveDate");
        } else if (null != params.get("expirationOrderBy")) {
            Sort.Direction direction = "DESC".equals(params.get("expirationOrderBy")) ? Sort.Direction.DESC : Sort.Direction.ASC;
            expirationOrder = new Sort.Order(direction, "routeComputeRuleCondition.expirationDate");
        }
        if (null != effectiveOrder && null == expirationOrder) {
            query.with(new Sort(effectiveOrder));
        } else if (null == effectiveOrder && null != expirationOrder) {
            query.with(new Sort(expirationOrder));
        } else if (null != effectiveOrder && null != expirationOrder) {
            query.with(new Sort(effectiveOrder, expirationOrder));
        } else {
            query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "ruleMetaInfo.addTime")));
        }
        LOGGER.info("查询应收规则列表入参，{}", JSONObject.toJSONString(query));
        return mongoOpers.find(query, CommissionRule.class);
    }

    private Query ruleGeneralQuery(Map<String, Object> params) {
        Query query = new Query();
        //or的要一起处理。不然会报错
        List<Criteria> criteriaOrList = new ArrayList<>();

        if (params.get("id") != null) {
            query.addCriteria(Criteria.where("_id").regex(".*?" + params.get("id") + ".*"));
        }
        if (params.get("ruleId") != null) {
            query.addCriteria(Criteria.where("ruleMetaInfo.ruleId").is(params.get("ruleId")));
        }
        if (params.get("companyCode") != null) {
            query.addCriteria(Criteria.where("routeComputeRuleCondition.companyCode").is(params.get("companyCode")));
        }
        if (params.get("agencyAreaCode") != null) {
            query.addCriteria(Criteria.where("routeComputeRuleCondition.issueAgencyList").elemMatch(
                    Criteria.where("code").is(params.get("agencyAreaCode"))));
        }
        if (params.get("areaCode") != null) {
            String areaCode = (String) params.get("areaCode");
            StringBuilder areaCodeRegex = new StringBuilder();
            areaCodeRegex.append("^");
            areaCodeRegex.append(areaCode);
            areaCodeRegex.append(".*");
            Set<String> parentAreaCodeSet = AreaCodeUtils.fuzzyQueryParent(areaCode);
            for (String parentAreaCode : parentAreaCodeSet) {
                areaCodeRegex.append("|^");
                areaCodeRegex.append(parentAreaCode);
                areaCodeRegex.append("$");
            }
            query.addCriteria(Criteria.where("routeComputeRuleCondition.sysAreaList")
                    .elemMatch(Criteria.where("code").regex(areaCodeRegex.toString())));
        }
        if (params.get("effectiveStartDate") != null && params.get("effectiveEndDate") != null) {
            String effectiveStartDate = (String) params.get("effectiveStartDate");
            String effectiveEndDate = (String) params.get("effectiveEndDate");
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date startDate = simpleDateFormat.parse(effectiveStartDate);
                Date endDate = simpleDateFormat.parse(effectiveEndDate);
                query.addCriteria(Criteria.where("routeComputeRuleCondition.effectiveDate").lte(startDate));
                query.addCriteria(Criteria.where("routeComputeRuleCondition.expirationDate").gte(endDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        if (params.get("balanceType") != null) {
            query.addCriteria(Criteria.where("routeComputeRuleCondition.balanceType").is(params.get("balanceType")));
        }
        if (params.get("contractNo") != null) {
            query.addCriteria(Criteria.where("routeComputeRuleCondition.contractNo").is(params.get("contractNo")));
        }
        //contractType为ABCDE枚举
        if (params.get("contractType") != null) {
            query.addCriteria(Criteria.where("routeComputeRuleCondition.contractType").regex(".*?" + params.get("contractType") + ".*"));
        }
        //已无此字段
        //if (params.get("contractTypeId") != null) {
        //    query.addCriteria(Criteria.where("contractTypeId").is(params.get("contractTypeId")));
        //}
        if (params.get("status") != null) {
            query.addCriteria(Criteria.where("ruleMetaInfo.status").is(params.get("status")));
        } else if (params.get("statusList") == null) {
            if (params.get("notUnauthorized") != null) {
                List<Integer> statusList = new ArrayList<Integer>();

                statusList.add((Integer) params.get("notUnauthorized"));
                statusList.add(RuleStatusEnum.RULE_STATUS_DELETED.value());
                query.addCriteria(Criteria.where("ruleMetaInfo.status").nin(statusList));
            } else {
                query.addCriteria(Criteria.where("ruleMetaInfo.status").ne(RuleStatusEnum.RULE_STATUS_DELETED.value()));
            }
        }

        //出单机构
        if (StringUtils.isNotBlank((String) params.get("agencyName"))) {
            // 网销机构名称(agencyResource) 蜂巢机构(agencyListVOResource)
            Criteria ct = new Criteria();
            Criteria ct1 = Criteria.where("routeComputeRuleCondition.agencyResource").regex(".*?" + params.get("agencyName") + ".*");
            Criteria ct2 = Criteria.where("routeComputeRuleCondition.agencyListVOResource").regex(".*?" + params.get("agencyName") + ".*");
            ct.orOperator(ct1, ct2);
            criteriaOrList.add(ct);
        }
        // 出单机构Id查询
        if (StringUtils.isNotBlank((String) params.get("agencyId"))) {
            // 网销机构Id(sysAreaDTOList) 蜂巢机构Id(agencyListVO)
            Criteria ct = new Criteria();
            Criteria ct1 = Criteria.where("receivableRuleConfig.sysAreaDTOList").elemMatch(Criteria.where("id").is(params.get("agencyId")));
            Criteria ct2 = Criteria.where("routeComputeRuleCondition.agencyListVO").regex(".*?" + params.get("agencyId") + ".*");
            ct.orOperator(ct1, ct2);
            criteriaOrList.add(ct);
        }
        if (params.get("statusList") != null) {
            LOGGER.info("状态参数，{}", params.get("statusList"));
            Object statusList = params.get("statusList");
            if (statusList instanceof List) {
                query.addCriteria(Criteria.where("ruleMetaInfo.status").in((List) statusList).ne(RuleStatusEnum.RULE_STATUS_DELETED.value()));
            }
        }
        if (params.get("computeType") != null) {
            query.addCriteria(Criteria.where("routeComputeRuleCondition.computeType").is(params.get("computeType")));
        }
        if (null != params.get("excludeRuleId")) {
            query.addCriteria(Criteria.where("ruleMetaInfo.ruleId").ne(params.get("excludeRuleId")));
        }

        if (params.get("level2Name") != null) {
            List<LevelGroup> levelGroupList = (List<LevelGroup>) params.get("level2Name");
            if (CollectionUtils.isNotEmpty(levelGroupList)) {
                Integer groupType = 1;
                Integer sencetype = 2;
                List<Integer> groupList = levelGroupList.stream()
                        .filter(levelGroup -> groupType.equals(levelGroup.getType()) && StringUtils.isNotBlank(levelGroup.getName()))
                        .map(levelGroup -> levelGroup.getId())
                        .collect(Collectors.toList());
                List<Integer> senceList = levelGroupList.stream()
                        .filter(levelGroup -> sencetype.equals(levelGroup.getType()) && StringUtils.isNotBlank(levelGroup.getName()))
                        .map(levelGroup -> levelGroup.getId())
                        .collect(Collectors.toList());
                List<Criteria> criteriaList = new ArrayList<>();
                if(CollectionUtils.isNotEmpty(groupList)){
                    //0表示全部帮派
                    groupList.add(0);
                    Criteria criteriaTmp = Criteria.where("payableCondition.sceneList.").elemMatch(
                            Criteria.where("level2.groupList.id").in(groupList).andOperator(Criteria.where("level2.groupList.type").is(groupType)));
                    criteriaList.add(criteriaTmp);
                }
                if(CollectionUtils.isNotEmpty(senceList)){
                    //0表示全部场景
                    senceList.add(0);
                    Criteria criteriaTmp = Criteria.where("payableCondition.sceneList.").elemMatch(
                            Criteria.where("level2.groupList.id").in(senceList).andOperator(Criteria.where("level2.groupList.type").is(sencetype)));
                    criteriaList.add(criteriaTmp);
                }
                Criteria[] array = new Criteria[criteriaList.size()];
                criteriaOrList.add(new Criteria().orOperator(criteriaList.toArray(array)));
            }
        }
        if (params.get("notImportOrderRule") != null && StringUtils.isBlank((String) params.get("contractType"))) {
            List<String> contractTypeEnumList = new ArrayList<>();
            contractTypeEnumList.add("B");
            contractTypeEnumList.add("C");
            contractTypeEnumList.add("D");
            query.addCriteria(Criteria.where("routeComputeRuleCondition.contractType").nin(contractTypeEnumList));
        }
        if (params.get("nullLevel2Channel") != null) {
            Boolean nullLevel2Channel = (Boolean) params.get("nullLevel2Channel");

            if (true == nullLevel2Channel.booleanValue()) {
                query.addCriteria(Criteria.where("payableCondition.sceneList").size(0));
            }
        }
        if (params.get("notSpeceficRatio") != null) {
            query.addCriteria(Criteria.where("routeComputeRuleCondition.computeType").ne(CommissionComputeTypeEnum.COMPUTER_TYPE_SPECIFIC_RATIO.value()));
        }
        if (params.get("contractCompanyIdList") != null) {
            List<Integer> contractCompanyIdList = (List<Integer>) params.get("contractCompanyIdList");
            query.addCriteria(Criteria.where("routeComputeRuleCondition.contractCompanyId").in(contractCompanyIdList));
        }
        if(CollectionUtils.isNotEmpty(criteriaOrList)){
            Criteria[] array = new Criteria[criteriaOrList.size()];
            query.addCriteria(new Criteria().andOperator(criteriaOrList.toArray(array)));
        }
        return query;
    }

}
