# costume-sales-system
costume-sales-system ---- my final case

# 表结构

## 总览

| **表名**              | **name**         | **估计行数**        | **备注**                                 |
| --------------------- | ---------------- | ------------------- | ---------------------------------------- |
| 服装种类          | costume_type     | 10种                | 上衣、裤子、帽子等                       |
| 商品种类          | items            | 300种商品           | 具体的某一个商品的信息(大小、性别)       |
| 门店              | stores           | 1000个门店          | 统计的全国的门店信息                     |
| 商品库存          | commodity_stocks | 30万                | 300 种商品 1000 个门店                   |
| 地区-区域         | area             | 7个                 | 东北，华北，华中，华南，华东，西北，西南 |
| 地区-省   | province         | 32个                |                                          |
| 地区-市   | city             | 294个               |                                          |
| 地区-区县 | district         | 2854个              |                                          |
| 销售记录          | sales_record     | 1000个门店 * 200/天 |                                          |

## costume_type

| **字段名**     | **类型**    | **默认** | **意义**   | **关联** |
| -------------- | ----------- | -------- | ---------- | -------- |
| **id**         | int(11)     | 自增     | id         |          |
| **is_deleted** | tinyint(4)  | 非       | is_deleted |          |
| **create_at**  | datetime    | now      | create_at  |          |
| **update_at**  | datetime    | now      | update_at  |          |
| **type_name**  | varchar(32) | 无       | 服装类型   |          |

## items

| **字段名**     | **类型**    | **默认** | **意义**   | **关联**             |
| -------------- | ----------- | -------- | ---------- | -------------------- |
| **id**         | int(11)     | 自增     | id         |                      |
| **is_deleted** | tinyint(4)  | 非       | is_deleted |                      |
| **create_at**  | datetime    | now      | create_at  |                      |
| **update_at**  | datetime    | now      | update_at  |                      |
| **item_name**  | varchar(64) | 无       | 服装名称   |                      |
| **costume_id** | int(11)     | 无       | 服装类别   | costume_type -> id   |
| **sex**        | tinyint(4)  | 无       | 性别       | 男1\|女0\|null无性别 |
| **item_size**  | tinyint(4)  | 无       | 服装大小   | SML\|etc             |

## stores

| **字段名**        | **类型**    | **默认** | **意义**   | **关联**       |
| ----------------- | ----------- | -------- | ---------- | -------------- |
| **id**            | int(11)     | 自增     | id         |                |
| **is_deleted**    | tinyint(4)  | 非       | is_deleted |                |
| **create_at**     | datetime    | now      | create_at  |                |
| **update_at**     | datetime    | now      | update_at  |                |
| **district_id**   | int(11)     | 0        | 区县的id   | district -> id |
| **store_name**    | varchar(64) | “服装店” | 店铺名称   |                |
| **store_address** | varchar(64) | 无       | 店铺地址   |                |
| **store_size**    | int(11)     | 0        | 店铺面积   |                |

## commodity_stocks

| **字段名**     | **类型**   | **默认** | **意义**   | **关联** |
| -------------- | ---------- | -------- | ---------- | -------- |
| **id**         | int(11)    | 自增     | id         |          |
| **is_deleted** | tinyint(4) | 非       | is_deleted |          |
| **create_at**  | datetime   | now      | create_at  |          |
| **update_at**  | datetime   | now      | update_at  |          |
| **store_id**   | int(11)    |          |            |          |
| **item_id**    | int(11)    |          |            |          |
| **amount**     | int(11)    |          |            |          |

## area

| **字段名**     | **类型**    | **默认** | **意义**   | **关联** |
| -------------- | ----------- | -------- | ---------- | -------- |
| **id**         | int(11)     | 自增     | id         |          |
| **is_deleted** | tinyint(4)  | 非       | is_deleted |          |
| **create_at**  | datetime    | now      | create_at  |          |
| **update_at**  | datetime    | now      | update_at  |          |
| **area_name**  | varchar(32) | 无       | 大区域名称 |          |

## province

| **字段名**      | **类型**    | **默认** | **意义**   | **关联**   |
| --------------- | ----------- | -------- | ---------- | ---------- |
| **id**          | int(11)     | 自增     | id         |            |
| **is_deleted**  | tinyint(4)  | 非       | is_deleted |            |
| **create_at**   | datetime    | now      | create_at  |            |
| **update_at**   | datetime    | now      | update_at  |            |
| **province_id** | varchar(32) | 无       | 省的名字   |            |
| **area_id**     | int(11)     | 无       | 大区域id   | area -> id |

## city

| **字段名**      | **类型**    | **默认** | **意义**   | **关联**       |
| --------------- | ----------- | -------- | ---------- | -------------- |
| **id**          | int(11)     | 自增     | id         |                |
| **is_deleted**  | tinyint(4)  | 非       | is_deleted |                |
| **create_at**   | datetime    | now      | create_at  |                |
| **update_at**   | datetime    | now      | update_at  |                |
| **city_name**   | varchar(32) | 无       | 城市名称   |                |
| **province_id** | int(11)     | 无       | 省的名称   | province -> id |

## district

| **字段名**        | **类型**    | **默认** | **意义**   | **关联**   |
| ----------------- | ----------- | -------- | ---------- | ---------- |
| **id**            | int(11)     | 自增     | id         |            |
| **is_deleted**    | tinyint(4)  | 非       | is_deleted |            |
| **create_at**     | datetime    | now      | create_at  |            |
| **update_at**     | datetime    | now      | update_at  |            |
| **district_name** | varchar(32) | 无       | 县区名称   |            |
| **city_id**       | int(11)     | 无       | 市的名称   | city -> id |

## sales_record

| **字段名**     | **类型**   | **默认** | **意义**   | **关联** |
| -------------- | ---------- | -------- | ---------- | -------- |
| **id**         | int(11)    | 自增     | id         |          |
| **is_deleted** | tinyint(4) | 非       | is_deleted |          |
| **create_at**  | datetime   | now      | create_at  |          |
| **update_at**  | datetime   | now      | update_at  |          |
| **store_id**   | int(11)    | 无       | 商店id     |          |
| **item_id**    | int(11)    | 无       | 商品id     |          |

# API

## 位置相关

| 描述                 | 名称 |
| -------------------- | ---- |
| 列出所有地区         |      |
| 按照地区列出所有省份 |      |
| 按照省份列出所有城市 |      |
| 按照城市列出所有县区 |      |
| 获取县区所属的城市   |      |
| 获取城市所属的省份   |      |
| 获取省份所属的区域   |      |

## 店铺相关

| 描述               | 名称 |
| ------------------ | ---- |
| 列出地区的所有店铺 |      |
| 列出省份的所有店铺 |      |
| 列出城市的所有店铺 |      |
| 列出区县的所有店铺 |      |
| 获取店铺所属的县区 |      |
| 获取店铺所属的城市 |      |
| 获取店铺所属的省份 |      |
| 获取店铺所属的区域 |      |

