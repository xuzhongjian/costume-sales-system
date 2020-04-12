package com.zjxu97.costume.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.Store;
import com.zjxu97.costume.model.vo.StoreVo;

import java.util.List;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
public interface StoreService extends IService<Store> {
    /**
     * 分页获取大区域的店铺
     *
     * @return 店铺的分页信息
     */
    IPage<Store> listStoresByLocation(int locationClass, int locationId, int size, int current);


    List<Store> listStoresByLocation(int locationClass, int locationId);

    /**
     * model list -> vo list
     *
     * @param storeList 店铺的model列表
     * @return 店铺的完全信息
     */
    List<StoreVo> getStoreVoFromModelList(List<Store> storeList);
}
