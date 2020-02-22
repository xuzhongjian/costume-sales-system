package com.zjxu97.costume.service.sale;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.dto.StockInOutDTO;
import com.zjxu97.costume.model.entity.sale.StockRecord;

import java.util.List;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
public interface StockRecordService extends IService<StockRecord> {
    /**
     * 库存变化记录
     *
     * @param stockInOutDTOList 需要写入的库存记录
     */
    void stockRecord(List<StockInOutDTO> stockInOutDTOList);
}
