package com.zjxu97.costume.model.vo;

import lombok.Data;

@Data
public class CommodityStockVo {
    private Integer id;
    private Integer storeId;
    private Integer itemId;
    private Integer amount;
}