package com.zjxu97.costume.commons;

/**
 * @author zjxu97
 * @date 2020-04-13 00:40
 */
public enum TimeTypeEnum {

    Day("Day"),
    Week("week"),
    Month("Month"),
    Quarter("Quarter"),
    Year("Year");

    private String value;

    TimeTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
