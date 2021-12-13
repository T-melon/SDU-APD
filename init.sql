SET FOREIGN_KEY_CHECKS = 0;

#先手动建库，免得以后remake的时候重复建库
use tapm;
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `uid`           int(18)   NOT NULL AUTO_INCREMENT,
    `userId`       int(18)   NOT NULL,
    #如果不用token
    `password`     char(255) NOT NULL,
    # else
    `token`        int(18)   NOT NULL,
    `refresh_token` int(18)            DEFAULT NULL,

    `identity`     char(2)   NOT NULL DEFAULT '技术',
    `mail`         char(255) NOT NULL,
    PRIMARY KEY (`uid`)
) ENGINE = InnoDB;

DROP TABLE IF EXISTS `demand`;
CREATE TABLE `demand`
(
    `did`    int(18)      NOT NULL AUTO_INCREMENT,
    `creater_id`    int(18)      NOT NULL,
    `priority`  int(1) NOT NULL default 0,
    `title` char(255) NOT NULL ,
    `create_time`  varchar(255) NOT NULL,
    `manager_id` int(1)       NOT NULL DEFAULT '2',
    `deadline`   int(1)       NOT NULL DEFAULT '1',
    `demand_document` char(255) NOT NULL ,
    # TODO 处理流程，不出意外会用数字去代表各个流程，待定
    `flow` text NOT NULL ,
    PRIMARY KEY (`did`),
    CONSTRAINT `demand._ibfk_1` FOREIGN KEY (`creater_id`) REFERENCES `user` (`uid`),
    CONSTRAINT `demand_ibfk_2` FOREIGN KEY (`manager_id`) REFERENCES `user` (`uid`)
) ENGINE = InnoDB;
