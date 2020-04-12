package com.zjxu97.costume.commons;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
public class CostumeConstants {
    public final static String API_PREFIX = "/api/v1";

    private static String[] sexStringArray = {"女款", "男款"};

    public static String getSexString(byte num) {
        return sexStringArray[num];
    }
}
