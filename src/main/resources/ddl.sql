CREATE TABLE `user` (
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