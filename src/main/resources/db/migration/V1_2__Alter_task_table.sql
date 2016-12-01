# ALTER TABLE task

ALTER TABLE `task`
  ADD `is_achieved` VARCHAR(50),
  ADD `is_final` VARCHAR(50);

# ALTER TABLE sub_task
# ALTER TABLE `sub_task`
#   ADD `is_achieved` VARCHAR(50);

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
  `task_id` BIGINT,
  `tag_id`  BIGINT,
  PRIMARY KEY (`task_id`, `tag_id`),
  FOREIGN KEY (`task_id`) REFERENCES `task` (`id`),
  FOREIGN KEY (`tag_id`) REFERENCES `tag` (`id`)
)