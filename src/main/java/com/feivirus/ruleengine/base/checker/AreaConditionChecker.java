package com.feivirus.ruleengine.base.checker;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.feivirus.ruleengine.base.ConditionChecker;
import com.feivirus.ruleengine.enums.RelationEnum;

/**
 * @author mofeng
 * @description:地区冲突校验
 * @date 2019/1/22
 */
public class AreaConditionChecker implements ConditionChecker<List<String>> {

    /**
     * 地区码
     */
    private List<String> srcAreaCodes;

    // 存在相交的数据
    private boolean hasSame = false;
    // 包含关系
    private boolean containRel = false;
    // 子集关系
    private boolean subsetRel = false;
    // src存在不同的数据
    private boolean srcHasDiff = false;
    // target存在不同的数据
    private boolean targetHasDiff = false;

    @Override
    public RelationEnum compare(List<String> targetAreaCodes) {
        if (CollectionUtils.isEmpty(srcAreaCodes) || CollectionUtils.isEmpty(targetAreaCodes)) {
            return RelationEnum.UNKNOWN;
        }
        if (CollectionUtils.isEmpty(srcAreaCodes) && CollectionUtils.isNotEmpty(targetAreaCodes)) {
            return RelationEnum.CONTAIN;
        }
        if (CollectionUtils.isNotEmpty(srcAreaCodes) && CollectionUtils.isEmpty(targetAreaCodes)) {
            return RelationEnum.SUBSET;
        }

        // 地区集合code的关系
        RelationEnum listRelationEnum = areaCodeRelation(srcAreaCodes, targetAreaCodes);
        if (listRelationEnum.equals(RelationEnum.SEPARATE)) {
            Integer separateNum = 0;
            for (String src : srcAreaCodes) {
                for (String target : targetAreaCodes) {
                    // 地区code的关系
                    RelationEnum codeRelation = areaCodeRelation(src, target);
                    if (false == codeRelation.equals(RelationEnum.SEPARATE)) {
                        return codeRelation;
                    } else {
                        separateNum = separateNum + 1;
                    }
                }
            }
            // 循环结束后，每个子项比较的结果都是相离，则返回相离关系
            if (separateNum == (srcAreaCodes.size() * targetAreaCodes.size())) {
                return RelationEnum.SEPARATE;
            }
        } else {
            return listRelationEnum;
        }
        return RelationEnum.UNKNOWN;
    }

    /**
     * 判断地区码的关系
     *
     * @param srcAreaCodes
     * @param targetAreaCodes
     * @return
     */
    public RelationEnum areaCodeRelation(List<String> srcAreaCodes, List<String> targetAreaCodes) {
        for (String src : srcAreaCodes) {
            if (targetAreaCodes.contains(src)) {
                hasSame = true;
            } else {
                targetHasDiff = true;
            }
        }
        for (String target : targetAreaCodes) {
            if (false == srcAreaCodes.contains(target)) {
                srcHasDiff = true;
            } else {
                hasSame = true;
            }
        }

        if (hasSame == true && targetHasDiff == true && srcHasDiff == false) {
            containRel = true;
        }
        if (hasSame == true && targetHasDiff == false && srcHasDiff == true) {
            subsetRel = true;
        }

        // 相离
        if (hasSame == false && srcHasDiff == true && targetHasDiff == true) {
            return RelationEnum.SEPARATE;
        }
        // 相交
        if (hasSame == true && srcHasDiff == true && targetHasDiff == true) {
            return RelationEnum.INTERSECT;
        }
        // 相等
        if (hasSame == true && srcHasDiff == false && targetHasDiff == false) {
            return RelationEnum.EQUAL;
        }
        // 包含
        if (hasSame == true && containRel == true && subsetRel == false) {
            return RelationEnum.CONTAIN;
        }
        // 子集
        if (hasSame == true && containRel == false && subsetRel == true) {
            return RelationEnum.SUBSET;
        }
        return RelationEnum.UNKNOWN;
    }

    /**
     * 判断地区码的关系
     *
     * @param srcCode
     * @param targetCode
     * @return
     */
    private RelationEnum areaCodeRelation(String srcCode, String targetCode) {
        if (srcCode.equals(targetCode)) {
            return RelationEnum.EQUAL;
        }
        if (srcCode.endsWith("00")) {
            srcCode = srcCode.substring(0, 4);
            if (srcCode.endsWith("00")) {
                srcCode = srcCode.substring(0, 2);
            }
        }
        if (targetCode.endsWith("00")) {
            targetCode = targetCode.substring(0, 4);
            if (targetCode.endsWith("00")) {
                targetCode = targetCode.substring(0, 2);
            }
        }
        if (srcCode.startsWith(targetCode)) {
            return RelationEnum.CONTAIN;
        }
        if (targetCode.startsWith(srcCode)) {
            return RelationEnum.SUBSET;
        }
        if (false == srcCode.startsWith(targetCode) && false == targetCode.startsWith(srcCode)) {
            return RelationEnum.SEPARATE;
        }
        return RelationEnum.UNKNOWN;
    }

    public List<String> getSrcAreaCodes() {
        return srcAreaCodes;
    }

    public void setSrcAreaCodes(List<String> srcAreaCodes) {
        this.srcAreaCodes = srcAreaCodes;
    }
}
