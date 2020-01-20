package com.zjxu97.costume.commons;

import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author zjxu97
 * @date 2020/1/20 10:26
 */
public class Common {
    public static boolean isUsefulNum(Number num) {
        return Objects.nonNull(num) && num.intValue() != 0;
    }

    public static boolean isUsefulString(String string) {
        return StringUtils.isEmpty(string);
    }

    public static boolean isUsefulList(List list) {
        return list != null && list.size() != 0;
    }
}
