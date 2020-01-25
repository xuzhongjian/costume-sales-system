package com.zjxu97.costume.model.param;

import lombok.Data;

import java.util.List;

/**
 * @author zjxu97
 * @date 2020/1/19 20:09
 */
@Data
public class GoodsParam {
    List<Integer> itemIds;
    Integer storeId;
}
