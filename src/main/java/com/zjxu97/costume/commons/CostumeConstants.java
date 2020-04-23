package com.zjxu97.costume.commons;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
public class CostumeConstants {
    public final static String API_PREFIX = "/api/v1";

    public final static String EMPTY = "empty";

    private static String[] sexStringArray = {"女款", "男款"};

    public static String getSexString(byte num) {
        return sexStringArray[num];
    }


    /**
     * @see Control
     */
    public final static String DATE = "date";

    public final static String LOCATION = "location";

    public final static String ITEM_TYPE = "itemType";

    public final static String SEX = "sex";

    public final static String ITEM = "item";

    public final static String SIZE = "size";
}
