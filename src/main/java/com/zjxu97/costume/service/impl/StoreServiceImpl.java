package com.zjxu97.costume.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.commons.LocationClassConstants;
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
    public IPage<Store> listStoresByLocation(int locationClass, int locationId, int size, int current) {
        switch (locationClass) {
            case LocationClassConstants.AREA:
                return this.getStoreVos(MAX_AREA_DIST_NUM, locationId, size, current);
            case LocationClassConstants.PROVINCE:
                return this.getStoreVos(MAX_PROV_DIST_NUM, locationId, size, current);
            case LocationClassConstants.CITY:
                return this.getStoreVos(MAX_CITY_DIST_NUM, locationId, size, current);
            case LocationClassConstants.DISTRICT:
                QueryWrapper<Store> qw = qw().eq("district_id", locationId);
                Page<Store> page = new Page<>();
                page.setCurrent(current);
                page.setSize(size);
                return this.page(page, qw);
            default:
                return null;
        }

    }

    private IPage<Store> getStoreVos(int maxAreaDistNum, int locationId, int size, int current) {
        Page<Store> page = new Page<>();
        page.setCurrent(current);
        page.setSize(size);
        QueryWrapper<Store> qw = qw().between("district_id", locationId, locationId + maxAreaDistNum);

        return this.page(page, qw);
    }

    @Override
    public List<Store> listStoresByLocation(int locationClass, int locationId) {
        switch (locationClass) {
            case LocationClassConstants.AREA:
                return this.getStoreVos(MAX_AREA_DIST_NUM, locationId);
            case LocationClassConstants.PROVINCE:
                return this.getStoreVos(MAX_PROV_DIST_NUM, locationId);
            case LocationClassConstants.CITY:
                return this.getStoreVos(MAX_CITY_DIST_NUM, locationId);
            case LocationClassConstants.DISTRICT:
                return this.list(qw().eq("district_id", locationId));
            default:
                return null;
        }

    }

    private List<Store> getStoreVos(int maxAreaDistNum, int locationId) {
        QueryWrapper<Store> qw = qw().between("district_id", locationId, locationId + maxAreaDistNum);
        return this.list(qw);
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
