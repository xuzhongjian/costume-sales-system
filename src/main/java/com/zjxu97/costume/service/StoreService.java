package com.zjxu97.costume.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxu97.costume.model.entity.Store;

/**
 * @author zjxu97
 * @date 2020/1/19 18:16
 */
public interface StoreService extends IService<Store> {

    Page<Store> listStoresByLocation(int locationId, int size, int current);

}
