package com.zjxu97.costume.service.costume.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.model.ItemType;
import com.zjxu97.costume.mapper.CostumeTypeMapper;
import com.zjxu97.costume.service.costume.CostumeTypeService;
import org.springframework.stereotype.Service;

@Service
public class CostumeTypeServiceImpl extends ServiceImpl<CostumeTypeMapper, ItemType> implements CostumeTypeService {
}
