package com.zjxu97.costume.commons;

public class CostumeConstants {
    public final static String API_PREFIX = "/api/v1";

    private static String[] sexStringArray = {"女款", "男款"};

    public static String getString(byte num) {
        return sexStringArray[num];
    }
}
