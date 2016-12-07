# ADD TABLE tag
CREATE TABLE `tag` (
  `id`      BIGINT AUTO_INCREMENT,
  `name`    VARCHAR(100),
  `user_id` BIGINT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

# ADD TASK_TAG
CREATE TABLE `task_tag` (
  `id` BIGINT AUTO_INCREMENT,
  `task_id` BIGINT,
  `tag_id`  BIGINT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`task_id`) REFERENCES `task` (`id`),
  FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
)