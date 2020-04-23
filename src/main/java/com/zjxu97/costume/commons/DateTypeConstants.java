package com.zjxu97.costume.commons;

import com.google.common.collect.ImmutableSet;

import java.util.Set;

/**
 * @author zjxu97
 * @date 2020-04-22 21:02
 */
public class DateTypeConstants {

    public static final String YEAR = "year";

    public static final String QUARTER = "quarter";

    public static final String MONTH = "month";

    public static final String WEEK = "week";

    public static final String DAY = "day";

    public static final Set<String> TypeSet = ImmutableSet.of(YEAR, MONTH, QUARTER, WEEK, DAY);
}