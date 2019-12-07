SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;



DROP TABLE IF EXISTS `costume_type`;
DROP TABLE IF EXISTS `items`;
DROP TABLE IF EXISTS `stores`;
DROP TABLE IF EXISTS `area`;
DROP TABLE IF EXISTS `province`;
DROP TABLE IF EXISTS `city`;
DROP TABLE IF EXISTS `district`;
DROP TABLE IF EXISTS `sales_record`;
DROP TABLE IF EXISTS `commodity_stocks`;


CREATE TABLE `costume_type`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `is_deleted` tinyint(4)       NOT NULL DEFAULT '0' COMMENT '是否删除,1删除，0未删除',
    `create_at`  datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `type_name`  varchar(64)      NOT NULL COMMENT '类型名称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='服装类型';

CREATE TABLE `items`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `is_deleted` tinyint(4)       NOT NULL DEFAULT '0' COMMENT '是否删除,1删除，0未删除',
    `create_at`  datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `item_name`  varchar(64)      NOT NULL COMMENT '名称',
    `costume_id` int(11) unsigned NOT NULL COMMENT '服装类别的id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='服装列表';

CREATE TABLE `stores`
(
    `id`            int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `is_deleted`    tinyint(4)       NOT NULL DEFAULT '0' COMMENT '是否删除,1删除，0未删除',
    `create_at`     datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`     datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `district_id`   int(11) unsigned NOT NULL DEFAULT '0' COMMENT '区县的id',
    `store_name`    varchar(64)      NOT NULL DEFAULT '服装店' COMMENT '店铺的名称',
    `store_address` varchar(64)      NOT NULL DEFAULT '无' COMMENT '地址',
    `store_size`    int(11)          NOT NULL DEFAULT '0' COMMENT '店铺面积',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='商店列表';

CREATE TABLE `commodity_stocks`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `is_deleted` tinyint(4)       NOT NULL DEFAULT '0' COMMENT '是否删除,1删除，0未删除',
    `create_at`  datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `store_id`   int(11) unsigned NOT NULL DEFAULT '0' COMMENT '店铺id',
    `item_id`    int(11) unsigned NOT NULL DEFAULT '0' COMMENT '商品id',
    `amount`     int(11)          NOT NULL DEFAULT '0' COMMENT '剩余库存',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='库存记录';


CREATE TABLE `area`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `is_deleted` tinyint(4)       NOT NULL DEFAULT '0' COMMENT '是否删除,1删除，0未删除',
    `create_at`  datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `area_name`  varchar(32)      NOT NULL COMMENT '大区域名称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='大区域名称';

DROP TABLE IF EXISTS `province`;
CREATE TABLE `province`
(
    `id`            int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `is_deleted`    tinyint(4)       NOT NULL DEFAULT '0' COMMENT '是否删除,1删除，0未删除',
    `create_at`     datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`     datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `province_name` varchar(32)      NOT NULL COMMENT '省的名称',
    `area_id`       int(11) unsigned NOT NULL DEFAULT '0' COMMENT '区域id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='省级单位';

CREATE TABLE `city`
(
    `id`          int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `is_deleted`  tinyint(4)       NOT NULL DEFAULT '0' COMMENT '是否删除,1删除，0未删除',
    `create_at`   datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`   datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `city_name`   varchar(32)      NOT NULL COMMENT '市名称',
    `province_id` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '省级id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='市级单位';

CREATE TABLE `district`
(
    `id`            int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `is_deleted`    tinyint(4)       NOT NULL DEFAULT '0' COMMENT '是否删除,1删除，0未删除',
    `create_at`     datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`     datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `district_name` varchar(32)      NOT NULL COMMENT '区县名称',
    `city_id`       int(11) unsigned NOT NULL DEFAULT '0' COMMENT '市级id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='区县单位';

CREATE TABLE `sales_record`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `is_deleted` tinyint(4)       NOT NULL DEFAULT '0' COMMENT '是否删除,1删除，0未删除',
    `create_at`  datetime                  DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`  datetime         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `store_id`   int(11) unsigned NOT NULL DEFAULT '0' COMMENT '店铺id',
    `item_id`    int(11) unsigned NOT NULL DEFAULT '0' COMMENT '商品id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='销售记录';