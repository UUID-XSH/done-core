# user
CREATE TABLE `user` (
  `id`                   BIGINT   AUTO_INCREMENT,
  `name`                 VARCHAR(50) UNIQUE,
  `pass_word`            VARCHAR(50),
  `nick_name`            VARCHAR(100),
  `email`                VARCHAR(200),
  `register_at`          DATETIME DEFAULT NULL,
  `recent_login_time_at` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`)
);

# project
CREATE TABLE `project` (
  `id`          BIGINT AUTO_INCREMENT,
  `name`        VARCHAR(1000),
  `create_time` TIMESTAMP,
  `achieved`    VARCHAR(50) COMMENT '是否完成',
  `archived`    VARCHAR(50) COMMENT '是否归档',
  `user_id`     BIGINT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

# task
CREATE TABLE `task` (
  `id`          BIGINT AUTO_INCREMENT,
  `name`        VARCHAR(50),
  `detail`      VARCHAR(1000),
  `is_achieved` VARCHAR(50),
  `project_id`  BIGINT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
);

# single task
CREATE TABLE `single_task` (
  `id`          BIGINT AUTO_INCREMENT,
  `name`        VARCHAR(50),
  `detail`      VARCHAR(1000),
  `user_id`     BIGINT,
  `is_achieved` VARCHAR(50),
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

#
CREATE TABLE `tomato` (
  `id`         BIGINT AUTO_INCREMENT,
  `start_time` DATETIME DEFAULT NULL,
  `end_time`   DATETIME DEFAULT NULL ,
  `task_id`    BIGINT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
);