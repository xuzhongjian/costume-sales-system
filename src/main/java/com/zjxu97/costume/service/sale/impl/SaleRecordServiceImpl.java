package com.zjxu97.costume.service.sale.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.model.SaleRecord;
import com.zjxu97.costume.mapper.SaleRecordMapper;
import com.zjxu97.costume.service.sale.SaleRecordService;
import org.springframework.stereotype.Service;

@Service
public class SaleRecordServiceImpl extends ServiceImpl<SaleRecordMapper, SaleRecord> implements SaleRecordService {
}
