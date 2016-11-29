# user
CREATE TABLE `User` (
  `id`   BIGINT AUTO_INCREMENT,
  `name` VARCHAR(50),
  PRIMARY KEY (`id`)
);

# project
CREATE TABLE `Project` (
  `id`          BIGINT AUTO_INCREMENT,
  `name`        VARCHAR(1000),
  `create_time` DATETIME,
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

# subTask
CREATE TABLE `Sub_Task` (
  `id`      BIGINT AUTO_INCREMENT,
  `name`    VARCHAR(50),
  `detail`  VARCHAR(1000),
  `task_id` BIGINT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`task_id`) REFERENCES `Task` (`id`)
)