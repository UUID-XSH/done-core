# user
CREATE TABLE `User` (
  `id`   BIGINT AUTO_INCREMENT,
  `name` VARCHAR(50) UNIQUE ,
  `pass_word` VARCHAR(50),
  `nick_name` VARCHAR(100),
  `email` VARCHAR(200),
  `register_date` TIMESTAMP,
  `recent_login_time` TIMESTAMP,
  PRIMARY KEY (`id`)
);

# project
CREATE TABLE `Project` (
  `id`          BIGINT AUTO_INCREMENT,
  `name`        VARCHAR(1000),
  `create_time` TIMESTAMP,
  `achieved`    VARCHAR(50) COMMENT '是否完成',
  `archived`    VARCHAR(50) COMMENT '是否归档',
  `user_id`     BIGINT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
);

# task
CREATE TABLE `Task` (
  `id`         BIGINT AUTO_INCREMENT,
  `name`       VARCHAR(50),
  `detail`     VARCHAR(1000),
  `project_id` BIGINT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`project_id`) REFERENCES `Project` (`id`)
);

# single task
CREATE TABLE `Single_task` (
  `id`         BIGINT AUTO_INCREMENT,
  `name`       VARCHAR(50),
  `detail`     VARCHAR(1000),
  `user_id` BIGINT,
  `is_achieved` VARCHAR(50),
  `is_final` VARCHAR(50),
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
);

#
CREATE TABLE `tomato` (
  `id`         BIGINT AUTO_INCREMENT,
  `start_time` TIMESTAMP,
  `end_time`   TIMESTAMP,
  `task_id`    BIGINT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
);

# # subTask
# CREATE TABLE `Sub_Task` (
#   `id`      BIGINT AUTO_INCREMENT,
#   `name`    VARCHAR(50),
#   `detail`  VARCHAR(1000),
#   `task_id` BIGINT,
#   PRIMARY KEY (`id`),
#   FOREIGN KEY (`task_id`) REFERENCES `Task` (`id`)
# )