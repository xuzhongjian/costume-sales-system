-- auto-generated definition
create table area
(
    id         int(11) unsigned                   not null comment '自增id'
        primary key,
    is_deleted tinyint  default 0                 not null comment '是否删除,1删除，0未删除',
    create_at  datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_at  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    area_name  varchar(32)                        not null comment '大区域名称'
) DEFAULT CHARACTER SET = utf8,comment '大区域名称';


-- auto-generated definition
create table city
(
    id          int(11) unsigned                           not null comment '自增id'
        primary key,
    is_deleted  tinyint          default 0                 not null comment '是否删除,1删除，0未删除',
    create_at   datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_at   datetime         default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    city_name   varchar(32)                                not null comment '市名称',
    province_id int(11) unsigned default 0                 not null comment '省级id'
) DEFAULT CHARACTER SET = utf8, comment '市级单位';

-- auto-generated definition
create table district
(
    id            int(11) unsigned                           not null comment '自增id'
        primary key,
    is_deleted    tinyint          default 0                 not null comment '是否删除,1删除，0未删除',
    create_at     datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_at     datetime         default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    district_name varchar(32)                                not null comment '区县名称',
    city_id       int(11) unsigned default 0                 not null comment '市级id'
) DEFAULT CHARACTER SET = utf8, comment '区县单位';


-- auto-generated definition
create table item
(
    id           int(11) unsigned auto_increment comment '自增id'
        primary key,
    is_deleted   tinyint  default 0                 not null comment '是否删除,1删除，0未删除',
    create_at    datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_at    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    item_name    varchar(64)                        not null comment '名称',
    item_type_id int(11) unsigned                   not null comment '服装类别的id'
) DEFAULT CHARACTER SET = utf8, comment '服装列表';


-- auto-generated definition
create table item_detail
(
    id           int(11) unsigned auto_increment comment '自增id'
        primary key,
    is_deleted   tinyint  default 0                 not null comment '是否删除,1删除，0未删除',
    create_at    datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_at    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    item_id      int(11) unsigned                   not null comment '款式id',
    item_size_id int                                null comment '服装大小',
    price        int      default 0                 not null comment '价格'
) DEFAULT CHARACTER SET = utf8, comment '鞋服具体';


-- auto-generated definition
create table item_size
(
    id           int(11) unsigned auto_increment comment '自增id'
        primary key,
    is_deleted   tinyint  default 0                 not null comment '是否删除,1删除，0未删除',
    create_at    datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_at    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    display_name varchar(64)                        not null comment '类型名称'
) DEFAULT CHARACTER SET = utf8, comment '鞋服大小';


-- auto-generated definition
create table item_type
(
    id         int(11) unsigned auto_increment comment '自增id'
        primary key,
    is_deleted tinyint  default 0                 not null comment '是否删除,1删除，0未删除',
    create_at  datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_at  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    sex        tinyint                            null comment '性别:男1|女0|null无性别',
    type_name  varchar(64)                        not null comment '类型名称:鞋、衣服'
) DEFAULT CHARACTER SET = utf8, comment '服装类型';


-- auto-generated definition
create table province
(
    id            int(11) unsigned                           not null comment '自增id'
        primary key,
    is_deleted    tinyint          default 0                 not null comment '是否删除,1删除，0未删除',
    create_at     datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_at     datetime         default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    province_name varchar(32)                                not null comment '省的名称',
    area_id       int(11) unsigned default 0                 not null comment '区域id'
) DEFAULT CHARACTER SET = utf8, comment '省级单位';


-- auto-generated definition
create table sale_record
(
    id             int(11) unsigned auto_increment comment '自增id'
        primary key,
    is_deleted     tinyint          default 0                 not null comment '是否删除,1删除，0未删除',
    create_at      datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_at      datetime         default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    store_id       int(11) unsigned default 0                 not null comment '店铺id',
    item_detail_id int(11) unsigned default 0                 not null comment '商品id',
    sale_type      tinyint          default 1                 null comment '1售出|2退回',
    age            int              default 0                 not null comment '购买者年龄'
) DEFAULT CHARACTER SET = utf8, comment '销售记录';


-- auto-generated definition
create table stock
(
    id             int(11) unsigned auto_increment comment '自增id'
        primary key,
    is_deleted     tinyint          default 0                 not null comment '是否删除,1删除，0未删除',
    create_at      datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_at      datetime         default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    store_id       int(11) unsigned default 0                 not null comment '店铺id',
    item_detail_id int(11) unsigned default 0                 not null comment '商品id',
    amount         int              default 0                 not null comment '剩余库存'
) DEFAULT CHARACTER SET = utf8, comment '库存信息';


-- auto-generated definition
create table stock_record
(
    id             int(11) unsigned auto_increment comment '自增id'
        primary key,
    is_deleted     tinyint          default 0                 not null comment '是否删除,1删除，0未删除',
    create_at      datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_at      datetime         default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    store_id       int(11) unsigned default 0                 not null comment '店铺id',
    item_detail_id int(11) unsigned default 0                 not null comment '商品id',
    inout_type     tinyint          default 0                 not null comment '出入类型',
    amount         int              default 0                 not null comment '数量'
) DEFAULT CHARACTER SET = utf8, comment '库存出入信息';

-- auto-generated definition
create table store
(
    id            int(11) unsigned auto_increment comment '自增id'
        primary key,
    is_deleted    tinyint          default 0                 not null comment '是否删除,1删除，0未删除',
    create_at     datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_at     datetime         default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    district_id   int(11) unsigned default 0                 not null comment '区县的id',
    store_name    varchar(64)      default '服装店'             not null comment '店铺的名称',
    store_address varchar(64)      default '无'               not null comment '地址'
) DEFAULT CHARACTER SET = utf8, comment '商店列表';


-- auto-generated definition
create table users
(
    id         int(11) unsigned auto_increment comment '自增id'
        primary key,
    is_deleted tinyint          default 0                                  not null comment '是否删除,1删除，0未删除',
    create_at  datetime         default CURRENT_TIMESTAMP                  null comment '创建时间',
    update_at  datetime         default CURRENT_TIMESTAMP                  not null on update CURRENT_TIMESTAMP comment '修改时间',
    user_id    varchar(32)                                                 not null comment '用户名称',
    nick_name  varchar(32)      default '新员工'                              not null comment '昵称',
    password   varchar(64)      default '25d55ad283aa400af464c76d713c07ad' not null comment '12345678的32位md5',
    store_id   int(11) unsigned default 0                                  not null comment '所属的店铺id',
    constraint users_user_id_uindex
        unique (user_id)
) DEFAULT CHARACTER SET = utf8, comment '用户表';
