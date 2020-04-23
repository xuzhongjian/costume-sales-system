package com.zjxu97.costume.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.commons.Control;
import com.zjxu97.costume.commons.DataElement;
import com.zjxu97.costume.model.entity.SaleRecord;

import java.util.List;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
public interface SaleRecordService extends IService<SaleRecord> {

    List<DataElement> getDataList(Control control, String from, String to, String xType, String xValue, String yValue);
}
