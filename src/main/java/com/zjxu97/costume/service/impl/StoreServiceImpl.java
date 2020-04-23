package com.zjxu97.costume.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.mapper.StoreMapper;
import com.zjxu97.costume.model.entity.Store;
import com.zjxu97.costume.service.StoreService;
import org.springframework.stereotype.Service;

/**
 * @author zjxu97
 * @date 2019/12/30 18:19
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

    @Override
    public Page<Store> listStoresByLocation(int locationId, int size, int current) {
        Page<Store> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);

        return this.page(page, qw().likeRight("district_id", locationId));
    }

    private QueryWrapper<Store> qw() {
        return new QueryWrapper<>();
    }
}
