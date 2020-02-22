package com.zjxu97.costume.service.store;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.Store;
import com.zjxu97.costume.model.param.KeyWordsPageParam;
import com.zjxu97.costume.model.param.LocationIdPageParam;
import com.zjxu97.costume.model.vo.StoreVo;

import java.util.List;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
public interface StoreService extends IService<Store> {
    /**
     * 分页获取区县的店铺
     *
     * @param locationIdPageParam 位置信息 + 分页
     * @return 店铺的分页信息
     */
    IPage<Store> listStoresByDist(LocationIdPageParam locationIdPageParam);

    /**
     * 分页获取城市的店铺
     *
     * @param locationIdPageParam 位置信息 + 分页
     * @return 店铺的分页信息
     */
    IPage<Store> listStoresByCity(LocationIdPageParam locationIdPageParam);

    /**
     * 分页获取省的店铺
     *
     * @param locationIdPageParam 位置信息 + 分页
     * @return 店铺的分页信息
     */
    IPage<Store> listStoresByProv(LocationIdPageParam locationIdPageParam);

    /**
     * 分页获取大区域的店铺
     *
     * @param locationIdPageParam 位置信息 + 分页
     * @return 店铺的分页信息
     */
    IPage<Store> listStoresByArea(LocationIdPageParam locationIdPageParam);

    /**
     * 根据关键字搜索店铺
     *
     * @param keyWordsPageParam 关键字参数 + 分页参数
     * @return 店铺的分页信息
     */
    IPage<Store> searchStores(KeyWordsPageParam keyWordsPageParam);

    /**
     * model list -> vo list
     *
     * @param storeList 店铺的model列表
     * @return 店铺的完全信息
     */
    List<StoreVo> getStoreVoFromModelList(List<Store> storeList);
}
