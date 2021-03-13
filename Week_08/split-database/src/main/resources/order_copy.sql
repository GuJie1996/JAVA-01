DROP TABLE IF EXISTS `order_copy1`;
CREATE TABLE `order_copy1`  (
  `id` bigint(20) UNSIGNED NOT NULL ,
  `count` int(10) UNSIGNED NOT NULL COMMENT '购买数量',
  `discount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '折扣金额',
  `amount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '总金额',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户',
  `goods_snapshot_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联商品快照',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
)
DROP TABLE IF EXISTS `order_copy2`;
CREATE TABLE `order_copy2`  (
  `id` bigint(20) UNSIGNED NOT NULL ,
  `count` int(10) UNSIGNED NOT NULL COMMENT '购买数量',
  `discount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '折扣金额',
  `amount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '总金额',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户',
  `goods_snapshot_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联商品快照',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
)
DROP TABLE IF EXISTS `order_copy3`;
CREATE TABLE `order_copy3`  (
  `id` bigint(20) UNSIGNED NOT NULL ,
  `count` int(10) UNSIGNED NOT NULL COMMENT '购买数量',
  `discount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '折扣金额',
  `amount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '总金额',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户',
  `goods_snapshot_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联商品快照',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
)
DROP TABLE IF EXISTS `order_copy4`;
CREATE TABLE `order_copy4`  (
  `id` bigint(20) UNSIGNED NOT NULL ,
  `count` int(10) UNSIGNED NOT NULL COMMENT '购买数量',
  `discount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '折扣金额',
  `amount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '总金额',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户',
  `goods_snapshot_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联商品快照',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
)
DROP TABLE IF EXISTS `order_copy5`;
CREATE TABLE `order_copy5`  (
  `id` bigint(20) UNSIGNED NOT NULL ,
  `count` int(10) UNSIGNED NOT NULL COMMENT '购买数量',
  `discount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '折扣金额',
  `amount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '总金额',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户',
  `goods_snapshot_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联商品快照',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
)
DROP TABLE IF EXISTS `order_copy6`;
CREATE TABLE `order_copy6`  (
  `id` bigint(20) UNSIGNED NOT NULL ,
  `count` int(10) UNSIGNED NOT NULL COMMENT '购买数量',
  `discount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '折扣金额',
  `amount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '总金额',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户',
  `goods_snapshot_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联商品快照',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
)
DROP TABLE IF EXISTS `order_copy7`;
CREATE TABLE `order_copy7`  (
  `id` bigint(20) UNSIGNED NOT NULL ,
  `count` int(10) UNSIGNED NOT NULL COMMENT '购买数量',
  `discount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '折扣金额',
  `amount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '总金额',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户',
  `goods_snapshot_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联商品快照',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
)
DROP TABLE IF EXISTS `order_copy8`;
CREATE TABLE `order_copy8`  (
  `id` bigint(20) UNSIGNED NOT NULL ,
  `count` int(10) UNSIGNED NOT NULL COMMENT '购买数量',
  `discount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '折扣金额',
  `amount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '总金额',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户',
  `goods_snapshot_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联商品快照',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
)
DROP TABLE IF EXISTS `order_copy9`;
CREATE TABLE `order_copy9`  (
  `id` bigint(20) UNSIGNED NOT NULL ,
  `count` int(10) UNSIGNED NOT NULL COMMENT '购买数量',
  `discount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '折扣金额',
  `amount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '总金额',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户',
  `goods_snapshot_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联商品快照',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
)
DROP TABLE IF EXISTS `order_copy10`;
CREATE TABLE `order_copy10`  (
  `id` bigint(20) UNSIGNED NOT NULL ,
  `count` int(10) UNSIGNED NOT NULL COMMENT '购买数量',
  `discount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '折扣金额',
  `amount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '总金额',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户',
  `goods_snapshot_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联商品快照',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
)
DROP TABLE IF EXISTS `order_copy11`;
CREATE TABLE `order_copy11`  (
  `id` bigint(20) UNSIGNED NOT NULL ,
  `count` int(10) UNSIGNED NOT NULL COMMENT '购买数量',
  `discount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '折扣金额',
  `amount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '总金额',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户',
  `goods_snapshot_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联商品快照',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
)
DROP TABLE IF EXISTS `order_copy12`;
CREATE TABLE `order_copy12`  (
  `id` bigint(20) UNSIGNED NOT NULL ,
  `count` int(10) UNSIGNED NOT NULL COMMENT '购买数量',
  `discount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '折扣金额',
  `amount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '总金额',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户',
  `goods_snapshot_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联商品快照',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
)
DROP TABLE IF EXISTS `order_copy13`;
CREATE TABLE `order_copy13`  (
  `id` bigint(20) UNSIGNED NOT NULL ,
  `count` int(10) UNSIGNED NOT NULL COMMENT '购买数量',
  `discount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '折扣金额',
  `amount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '总金额',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户',
  `goods_snapshot_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联商品快照',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
)
DROP TABLE IF EXISTS `order_copy14`;
CREATE TABLE `order_copy14`  (
  `id` bigint(20) UNSIGNED NOT NULL ,
  `count` int(10) UNSIGNED NOT NULL COMMENT '购买数量',
  `discount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '折扣金额',
  `amount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '总金额',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户',
  `goods_snapshot_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联商品快照',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
)
DROP TABLE IF EXISTS `order_copy15`;
CREATE TABLE `order_copy15`  (
  `id` bigint(20) UNSIGNED NOT NULL ,
  `count` int(10) UNSIGNED NOT NULL COMMENT '购买数量',
  `discount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '折扣金额',
  `amount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '总金额',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户',
  `goods_snapshot_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联商品快照',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
)
DROP TABLE IF EXISTS `order_copy16`;
CREATE TABLE `order_copy16`  (
  `id` bigint(20) UNSIGNED NOT NULL ,
  `count` int(10) UNSIGNED NOT NULL COMMENT '购买数量',
  `discount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '折扣金额',
  `amount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '总金额',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户',
  `goods_snapshot_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联商品快照',
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
)