package com.zjxu97.costume.service.sale;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.dto.StockInOutDTO;
import com.zjxu97.costume.model.entity.sale.StockRecord;

import java.util.List;

public interface StockRecordService extends IService<StockRecord> {

    void stockRecord(List<StockInOutDTO> stockInOutDTOList);
}
