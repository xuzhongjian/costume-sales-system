package com.zjxu97.costume.param;

import lombok.Data;

/**
 * @author zjxu97
 * @date 2020/1/19 21:40
 */
@Data
public class StockInOutParam {
    private Integer storeId;
    private Integer itemId;
    private Integer amount;
}
