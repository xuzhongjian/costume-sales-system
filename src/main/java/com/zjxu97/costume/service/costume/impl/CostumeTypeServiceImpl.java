package com.zjxu97.costume.service.costume.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxu97.costume.model.CostumeType;
import com.zjxu97.costume.mapper.CostumeTypeMapper;
import com.zjxu97.costume.service.costume.CostumeTypeService;
import org.springframework.stereotype.Service;

@Service
public class CostumeTypeServiceImpl extends ServiceImpl<CostumeTypeMapper, CostumeType> implements CostumeTypeService {
}
