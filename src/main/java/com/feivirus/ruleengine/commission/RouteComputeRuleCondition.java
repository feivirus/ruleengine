package com.feivirus.ruleengine.commission;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.feivirus.ruleengine.rule.dto.SysAreaDTO;

/**
 * 交易路由相关信息
 *
 * @author feivirus
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteComputeRuleCondition {
    //适用地区对象
    private List<SysAreaDTO> sysAreaList;

    //适用地区(前端数据回显保留字段)
    private String areaResourceVO;

    //出单机构地区网销
    private List<SysAreaDTO> sysAreaDTOList;

    //出单机构地区网销(前端数据回显保留字段)
    private String agencyResource;

    //出单机构蜂巢
    private String agencyListVO;

    //出单机构蜂巢(前端数据回显保留字段)
    private String agencyListVOResource;

    //合同号
    private String contractNo;

    //参考ContractTypeEnum,ABCED,单选
    private String contractType;

    //险企code
    private String companyCode;

    private Integer contractCompanyId;

    //业务类型 网销/蜂巢 参考枚举BusinessType
    private Integer businessType;

    //商业险点数
    private Double bizPoint;

    //交强险点数
    private Double forcePoint;

    //服务费
    private Double serviceExpense;

    //生效时间
    private Date effectiveDate;

    //过期时间
    private Date expirationDate;

    @Transient
    private List<String> effectiveDateList;

    //计算类型  1 佣金比例 2 一车一价
    private Integer computeType;

    //结算方式 1净保费计算 2非净保费计算
    private Integer balanceType;

    public List<SysAreaDTO> getSysAreaList() {
        return sysAreaList;
    }

    public void setSysAreaList(List<SysAreaDTO> sysAreaList) {
        this.sysAreaList = sysAreaList;
    }

    public String getAreaResourceVO() {
        return areaResourceVO;
    }

    public void setAreaResourceVO(String areaResourceVO) {
        this.areaResourceVO = areaResourceVO;
    }

    public List<SysAreaDTO> getSysAreaDTOList() {
        return sysAreaDTOList;
    }

    public void setSysAreaDTOList(List<SysAreaDTO> sysAreaDTOList) {
        this.sysAreaDTOList = sysAreaDTOList;
    }

    public String getAgencyListVO() {
        return agencyListVO;
    }

    public void setAgencyListVO(String agencyListVO) {
        this.agencyListVO = agencyListVO;
    }

    public String getAgencyListVOResource() {
        return agencyListVOResource;
    }

    public void setAgencyListVOResource(String agencyListVOResource) {
        this.agencyListVOResource = agencyListVOResource;
    }

    public String getAgencyResource() {
        return agencyResource;
    }

    public void setAgencyResource(String agencyResource) {
        this.agencyResource = agencyResource;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Double getBizPoint() {
        return bizPoint;
    }

    public void setBizPoint(Double bizPoint) {
        this.bizPoint = bizPoint;
    }

    public Double getForcePoint() {
        return forcePoint;
    }

    public void setForcePoint(Double forcePoint) {
        this.forcePoint = forcePoint;
    }

    public Double getServiceExpense() {
        return serviceExpense;
    }

    public void setServiceExpense(Double serviceExpense) {
        this.serviceExpense = serviceExpense;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public List<String> getEffectiveDateList() {
        return effectiveDateList;
    }

    public void setEffectiveDateList(List<String> effectiveDateList) {
        this.effectiveDateList = effectiveDateList;
    }

    public Integer getComputeType() {
        return computeType;
    }

    public void setComputeType(Integer computeType) {
        this.computeType = computeType;
    }

    public Integer getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(Integer balanceType) {
        this.balanceType = balanceType;
    }

    public Integer getContractCompanyId() {
        return contractCompanyId;
    }

    public void setContractCompanyId(Integer contractCompanyId) {
        this.contractCompanyId = contractCompanyId;
    }
}