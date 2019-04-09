package com.feivirus.ruleengine.base.checker;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.feivirus.ruleengine.base.ConditionChecker;
import com.feivirus.ruleengine.enums.RelationEnum;

/**
 * 不可拆分String类型校验器
 * 例如：比较"B02,B05,B06"与"B02,B36,B33"的关系（因子中的B02是作为一个整体去比较的，为最小不可拆分因子）
 *
 * @author feivirus
 */
public class StringConditionChecker implements ConditionChecker<String> {

    private String value;
    private static String SPLIT = ",";

    @Override
    public RelationEnum compare(String target) {
        if (StringUtils.isBlank(value) || StringUtils.isBlank(target)) {
            return RelationEnum.UNKNOWN;
        }
        if (isEquals(target)) {
            return RelationEnum.EQUAL;
        }
        if (isContains(value, target)) {
            return RelationEnum.CONTAIN;
        }
        if (isContains(target, value)) {
            return RelationEnum.SUBSET;
        }
        if (separate(target)) {
            return RelationEnum.SEPARATE;
        }
        if (intersect(target)) {
            return RelationEnum.INTERSECT;
        }
        return RelationEnum.UNKNOWN;
    }

    /**
     * 判断字符串是否相等
     */
    private boolean isEquals(String target) {
        boolean flag = true;
        if (value.length() == target.length()) {
            List<String> values = Arrays.asList(value.split(SPLIT));
            String[] targets = target.split(SPLIT);
            for (int j = 0; j < targets.length; j++) {
                if (!values.contains(targets[j])) {
                    flag = false;
                }
            }
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * 判断str1是否包含str2(区分大小写)排除掉相等的情况
     */
    private boolean isContains(String str1, String str2) {
        boolean flag = true;
        if (str1.length() > str2.length()) {
            List<String> str1s = Arrays.asList(str1.split(SPLIT));
            String[] str2s = str2.split(SPLIT);
            for (int j = 0; j < str2s.length; j++) {
                if (!str1s.contains(str2s[j])) {
                    flag = false;
                }
            }
        } else {
            flag = false;
        }
        return flag;
    }

    /**
     * 相离关系判断
     */
    private boolean separate(String target) {
        boolean flag = true;
        List<String> values = Arrays.asList(value.split(SPLIT));
        String[] targets = target.split(SPLIT);
        for (int j = 0; j < targets.length; j++) {
            if (values.contains(targets[j])) {
                flag = false;
            }
        }
        return flag;
    }

    /**
     * 相交关系判断
     */
    private boolean intersect(String target) {
        int differ = 0;
        // 都包含的字符
        int same = 0;
        List<String> values = Arrays.asList(value.split(SPLIT));
        String[] targets = target.split(SPLIT);
        for (int j = 0; j < targets.length; j++) {
            if (!values.contains(targets[j])) {
                differ += 1;
            } else {
                same += 1;
            }
        }
        if (differ >= 1 && same >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
