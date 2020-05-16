create table area
(
    id         int(11) unsigned                   not null comment '自增id'
        primary key,
    is_deleted tinyint  default 0                 not null comment '是否删除,1删除，0未删除',
    create_at  datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_at  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    area_name  varchar(32)                        not null comment '大区域名称'
)
    comment '大区域名称';

create table city
(
    id          int(11) unsigned                           not null comment '自增id'
        primary key,
    is_deleted  tinyint          default 0                 not null comment '是否删除,1删除，0未删除',
    create_at   datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_at   datetime         default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    city_name   varchar(32)                                not null comment '市名称',
    province_id int(11) unsigned default 0                 not null comment '省级id'
)
    comment '市级单位';

create table district
(
    id            int(11) unsigned                           not null comment '自增id'
        primary key,
    is_deleted    tinyint          default 0                 not null comment '是否删除,1删除，0未删除',
    create_at     datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_at     datetime         default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    district_name varchar(32)                                not null comment '区县名称',
    city_id       int(11) unsigned default 0                 not null comment '市级id'
)
    comment '区县单位';

create table item
(
    id           int(11) unsigned auto_increment comment '自增id'
        primary key,
    is_deleted   tinyint  default 0                 not null comment '是否删除,1删除，0未删除',
    create_at    datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_at    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    item_name    varchar(64)                        not null comment '名称',
    item_type_id int(11) unsigned                   not null comment '服装类别的id'
)
    comment '服装列表';

create table item_big_type
(
    id         int(11) unsigned auto_increment comment '自增id'
        primary key,
    is_deleted tinyint  default 0                 not null comment '是否删除,1删除，0未删除',
    create_at  datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_at  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    type_name  varchar(64)                        not null comment '大类型名称:鞋、衣服'
)
    comment '服装类型';

create table item_detail
(
    id           int(11) unsigned auto_increment comment '自增id'
        primary key,
    is_deleted   tinyint  default 0                 not null comment '是否删除,1删除，0未删除',
    create_at    datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_at    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    item_id      int(11) unsigned                   not null comment '款式id',
    item_size_id int                                null comment '服装大小',
    price        int      default 0                 not null comment '价格',
    cost         int      default 0                 null comment '成本',
    sex          tinyint  default 2                 null comment '性别'
)
    comment '鞋服具体';

create table item_size
(
    id           int(11) unsigned auto_increment comment '自增id'
        primary key,
    is_deleted   tinyint  default 0                 not null comment '是否删除,1删除，0未删除',
    create_at    datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_at    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    display_name varchar(64)                        not null comment '类型名称'
)
    comment '鞋服大小';

create table item_type
(
    id               int(11) unsigned auto_increment comment '自增id'
        primary key,
    is_deleted       tinyint  default 0                 not null comment '是否删除,1删除，0未删除',
    create_at        datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_at        datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    type_name        varchar(64)                        not null comment '类型名称:鞋、衣服',
    item_big_type_id tinyint                            null comment '大类id'
)
    comment '服装类型';

create table province
(
    id            int(11) unsigned                           not null comment '自增id'
        primary key,
    is_deleted    tinyint          default 0                 not null comment '是否删除,1删除，0未删除',
    create_at     datetime         default CURRENT_TIMESTAMP null comment '创建时间',
    update_at     datetime         default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间',
    province_name varchar(32)                                not null comment '省的名称',
    area_id       int(11) unsigned default 0                 not null comment '区域id'
)
    comment '省级单位';

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
)
    comment '销售记录';

create index item_detail_id_index
    on sale_record (item_detail_id, store_id);

create index store_id_index
    on sale_record (store_id, item_detail_id);

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
)
    comment '商店列表';