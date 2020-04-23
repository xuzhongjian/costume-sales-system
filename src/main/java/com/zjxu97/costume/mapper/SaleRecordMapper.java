package com.zjxu97.costume.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjxu97.costume.commons.Control;
import com.zjxu97.costume.commons.DataElement;
import com.zjxu97.costume.commons.DisplayTypeConstants;
import com.zjxu97.costume.model.entity.SaleRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 * @see DisplayTypeConstants
 */
@Mapper
public interface SaleRecordMapper extends BaseMapper<SaleRecord> {
    List<DataElement> getDateCount(@Param("control") Control control, @Param("from") String from, @Param("to") String to, @Param("dateType") String dateType);


    List<DataElement> getDateAmount(@Param("control") Control control, @Param("from") String from, @Param("to") String to, @Param("dateType") String dateType);
}