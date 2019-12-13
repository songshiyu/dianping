CREATE TABLE "dianping".`user` (
`id`  int NOT NULL AUTO_INCREMENT ,
`create_at`  datetime NOT NULL DEFAULT '0000-00-00 00:00:00' ,
`update_at`  datetime NOT NULL DEFAULT '0000-00-00 00:00:00' ,
`telphone`  varchar(40) NOT NULL ,
`password`  varchar(200) NOT NULL DEFAULT null ,
`nick_name`  varchar(40) NOT NULL ,
`gender`  int NOT NULL ,
PRIMARY KEY (`id`),
UNIQUE INDEX `telphone_unique_index` (`telphone`) USING BTREE
)
;

CREATE TABLE `dianping`.`seller`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL DEFAULT '',
  `created_at` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00',
  `updated_at` datetime(0) NOT NULL DEFAULT '0000-00-00 00:00:00',
  `remark_score` decimal(2, 1) NOT NULL DEFAULT 0,
  `disabled_flag` int(0) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
);