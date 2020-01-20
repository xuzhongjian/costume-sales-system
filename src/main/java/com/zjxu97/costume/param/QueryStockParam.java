package com.zjxu97.costume.param;

import lombok.Data;

/**
 * @author zjxu97
 * @date 2020/1/20 10:25
 */
@Data
public class QueryStockParam {
    private Integer storeId;

    private String itemKeyWords;
    private Integer costumeId;
    private Byte sex;
    private Integer itemSize;
}
