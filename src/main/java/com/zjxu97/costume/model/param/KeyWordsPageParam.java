package com.zjxu97.costume.model.param;

import com.zjxu97.costume.commons.PageParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zjxu97
 * @date 2020/1/31 21:41
 */

@Data
@ApiModel
@EqualsAndHashCode(callSuper = true)
public class KeyWordsPageParam extends PageParam {
    private String keyWords;
}
