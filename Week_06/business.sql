-- 用户
CREATE TABLE `business`.`user`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL COMMENT '用户名',
  `phone` varchar(20) NULL COMMENT '手机号',
  `password` char(32) NOT NULL COMMENT 'md5密码',
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name`(`name`)
);

-- 商品
CREATE TABLE `business`.`goods`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '商品名',
  `price` decimal(13, 2) UNSIGNED NOT NULL COMMENT '单价',
	`count` int UNSIGNED NOT NULL COMMENT '库存数量',
	`goods_extra_id` bigint UNSIGNED NOT NULL COMMENT '关联商品详情',
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `name_price`(`name`, `price`)
);

-- 商品详情
CREATE TABLE `business`.`goods_extra`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL COMMENT '描述',
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`)
);

-- 商品快照
CREATE TABLE `business`.`goods_snapshot`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '商品名',
  `price` decimal(13, 2) UNSIGNED NOT NULL COMMENT '单价',
	`goods_id` bigint UNSIGNED NOT NULL COMMENT '关联商品',
	`goods_extra_id` bigint UNSIGNED NOT NULL COMMENT '关联商品详情',
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`)
);

-- 订单
CREATE TABLE `business`.`order`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `count` int UNSIGNED NOT NULL COMMENT '购买数量',
  `discount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '折扣金额',
  `amount` decimal(13, 2) UNSIGNED NOT NULL COMMENT '总金额',
	`user_id` bigint UNSIGNED NOT NULL COMMENT '关联用户',
  `goods_snapshot_id` bigint UNSIGNED NOT NULL COMMENT '关联商品快照',
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`)
);