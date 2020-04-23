package com.zjxu97.costume.commons;

import org.apache.logging.log4j.util.Strings;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public static boolean isUselessList(List list) {
        return Objects.isNull(list) || list.size() == 0;
    }

    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    public static boolean controlValueNotEmpty(String value) {
        return Strings.isNotBlank(value) && !CostumeConstants.EMPTY.equals(value);
    }

}
