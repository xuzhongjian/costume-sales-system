package com.zjxu97.costume.model.vo;

import lombok.Data;

@Data
public class StockVo {
    private Integer id;
    private StoreVo storeVo;
    private ItemDetailVo itemDetailVo;
    private Integer amount;
}