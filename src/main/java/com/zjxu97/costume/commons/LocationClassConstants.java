package com.zjxu97.costume.commons;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */

public class LocationClassConstants {
    private static final int MUST_LENGTH = 6;
    public static final byte ROOT = (byte) 0;

    public static final byte AREA = (byte) 1;
    public static final byte PROVINCE = (byte) 2;
    public static final byte CITY = (byte) 3;
    public static final byte DISTRICT = (byte) 4;

    // 用于获取ClassId
    private static final char ZERO_CHAR = '0';

    public static byte getLocationClass(int locationId) {
        char[] locationCharArray = String.valueOf(locationId).trim().toCharArray();
        int length = locationCharArray.length;

        switch (length) {
            case 1:
                return AREA;
            case 2:
                return PROVINCE;
            case 4:
                return CITY;
            case 6:
                return DISTRICT;
            default:
                return -1;
        }

    }
}
