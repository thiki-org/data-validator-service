
-- Data Structure

CREATE TABLE `vd_user` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `vd_product` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '',
  `prize` decimal(10,0) DEFAULT NULL,
  `description` text,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `vd_promotion` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` BIGINT(20) DEFAULT NULL COMMENT 'Product.id',
  `description` text,
  `status` int(11) DEFAULT NULL,
  `beginTime` date DEFAULT NULL,
  `endTime` date DEFAULT NULL,
  `discount` int(11) DEFAULT NULL COMMENT '折扣百分比（0-99）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `vd_comment` (
  `id` BIGINT(20) unsigned NOT NULL AUTO_INCREMENT,
  `target_id` BIGINT(20) DEFAULT NULL COMMENT 'Product.id or Promotion.id',
  `type` int(11) DEFAULT NULL COMMENT '1:product;2:promotion',
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `author` BIGINT(20) DEFAULT NULL COMMENT 'User.id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- Data for Testing



