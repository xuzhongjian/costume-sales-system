package com.zjxu97.costume.param;

import lombok.Data;

/**
 * @author zjxu97
 * @date 2019/12/29 23:58
 */
@Data
public class QueryItemsParam {
    private String itemKeyWords;
    private Integer costumeId;
    private Byte Sex;
    private Integer itemSize;
}