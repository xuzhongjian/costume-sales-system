package com.zjxu97.costume.model.dto;

import lombok.Data;

/**
 * @author zjxu97
 * @date 2020/1/25 19:34
 */
@Data
public class StockDisplayDTO {
    private Integer id;

    private Integer storeId;

    private Integer itemDetailId;

    private Integer amount;
}