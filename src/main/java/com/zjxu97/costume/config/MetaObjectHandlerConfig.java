package com.zjxu97.costume.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zjxu97
 * @date 2019/12/30 14:58
 */
@Component
public class MetaObjectHandlerConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        this.setFieldValByName("create_at", now, metaObject);
        this.setFieldValByName("update_at", now, metaObject);
        this.setFieldValByName("is_deleted", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date now = new Date();
        this.setFieldValByName("update_at", now, metaObject);
    }

}