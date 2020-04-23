package com.zjxu97.costume.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.mapper.StoreMapper;
import com.zjxu97.costume.model.entity.Store;
import com.zjxu97.costume.model.vo.StoreVo;
import com.zjxu97.costume.service.StoreService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zjxu97
 * @date 2019/12/30 18:19
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {
    private static final int MAX_AREA_DIST_NUM = 99999;
    private static final int MAX_PROV_DIST_NUM = 9999;
    private static final int MAX_CITY_DIST_NUM = 99;

    @Override
    public IPage<Store> listStoresByLocation(int locationId, int size, int current) {
        Page<Store> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        QueryWrapper<Store> qw = qw().likeRight("district_id", locationId);

        return this.page(page, qw);
    }

    @Override
    public List<StoreVo> getStoreVoFromModelList(List<Store> storeList) {
        return storeList.stream().map(store -> {
            StoreVo storeVo = new StoreVo();
            BeanUtils.copyProperties(store, storeVo);
            return storeVo;
        }).collect(Collectors.toList());
    }

    private QueryWrapper<Store> qw() {
        return new QueryWrapper<>();
    }
}
