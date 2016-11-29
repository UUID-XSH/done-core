# user
CREATE TABLE `User` (
  `id`   INT AUTO_INCREMENT,
  `name` VARCHAR(50),
  PRIMARY KEY (`id`)
);

# project
CREATE TABLE `Project` (
  `id`          INT AUTO_INCREMENT,
  `name`        VARCHAR(1000),
  `create_time` DATETIME,
  `achieved`    VARCHAR(50),
  `archived`    VARCHAR(50),
  `user_id`     INT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
);

# task
CREATE TABLE `Task` (
  `id`         INT AUTO_INCREMENT,
  `name`       VARCHAR(50),
  `detail`     VARCHAR(1000),
  `project_id` INT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`project_id`) REFERENCES `Project` (`id`)
);

# subTask
CREATE TABLE `Sub_Task` (
  `id`      INT AUTO_INCREMENT,
  `name`    VARCHAR(50),
  `detail`  VARCHAR(1000),
  `task_id` INT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`task_id`) REFERENCES `Task` (`id`)
)