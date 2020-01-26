package com.zjxu97.costume.model.param;

import lombok.Data;

/**
 * @author zjxu97
 * @date 2019/12/29 23:58
 */
@Data
public class QueryItemParam {
    private String itemKeyWords;
    private Integer itemTypeId;
    private Integer itemSizeId;
}