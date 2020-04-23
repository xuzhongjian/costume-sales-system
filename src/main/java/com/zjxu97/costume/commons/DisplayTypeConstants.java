package com.zjxu97.costume.commons;


import com.google.common.collect.ImmutableSet;

import java.util.Set;

/**
 * [销售总额 销售件数]
 *
 * @author zjxu97
 * @date 2020-04-22 13:21
 */
public class DisplayTypeConstants {

    public static final String SALE_COUNT = "sale-count";

    public static final String SALE_AMOUNT = "sale-amount";

    public static final Set<String> TypeSet = ImmutableSet.of(SALE_COUNT, SALE_AMOUNT);
}
