package com.zjxu97.costume.commons;

import java.util.Objects;

/**
 * @author zjxu97
 * @date 2020/1/20 10:26
 */
public class Common {
    public static boolean isUsefulNum(Number num) {
        return Objects.nonNull(num) && num.intValue() != 0;
    }
}
