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
    List<DataElement> getDateData(@Param("control") Control control,
                                  @Param("from") String from,
                                  @Param("to") String to,
                                  @Param("dateType") String dateType,
                                  @Param("dataType") String dataType);

    List<DataElement> getLocationData(@Param("control") Control control,
                                      @Param("from") String from,
                                      @Param("to") String to,
                                      @Param("locationType") String locationType,
                                      @Param("dataType") String dataType);

    List<DataElement> getItemTypeData(@Param("control") Control control,
                                      @Param("from") String from,
                                      @Param("to") String to,
                                      @Param("dataType") String dataType);

    List<DataElement> getItemData(@Param("control") Control control,
                                  @Param("from") String from,
                                  @Param("to") String to,
                                  @Param("dataType") String dataType);

    List<DataElement> getSizeData(@Param("control") Control control,
                                  @Param("from") String from,
                                  @Param("to") String to,
                                  @Param("dataType") String dataType);

    List<DataElement> getSexData(@Param("control") Control control,
                                 @Param("from") String from,
                                 @Param("to") String to,
                                 @Param("dataType") String dataType);
}