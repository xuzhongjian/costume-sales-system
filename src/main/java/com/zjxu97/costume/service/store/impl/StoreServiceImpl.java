package com.zjxu97.costume.service.store.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.model.Store;
import com.zjxu97.costume.mapper.StoreMapper;
import com.zjxu97.costume.service.store.StoreService;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {
}
