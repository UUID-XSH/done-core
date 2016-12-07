package info.xsh.done.core.service;

import info.xsh.done.core.BaseComponent;
import info.xsh.done.core.controller.vo.TagVo;
import info.xsh.done.core.domain.Tag;
import info.xsh.done.core.domain.Task;
import info.xsh.done.core.domain.TaskTag;
import info.xsh.done.core.repository.TagRepository;
import info.xsh.done.core.repository.TaskTagRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by xiaohuo on 16/12/7.
 */
@Service
@Slf4j
public class TagService extends BaseComponent {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TaskTagRepository taskTagRepository;


    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    /**
     * 添加用户标签
     *
     * @param tagVo
     * @param userId
     * @return
     */
    public Tag add(TagVo tagVo, long userId) {
        Optional<Tag> tagOptional = tagRepository.findByUserIdAndName(userId, tagVo.getName());
        if (tagOptional.isPresent()) {
            throw new RuntimeException("标签已存在");
        }
        Tag tag = convert(Tag.class, tagVo);
        tag.setUserId(userId);
        return this.save(tag);
    }

    /**
     * 删除用户标签
     *
     * @param userId
     * @param tagId
     */
    public void delete(long userId, long tagId) {
        Optional<Tag> tagOptional = tagRepository.findByIdAndUserId(tagId, userId);
        if (!tagOptional.isPresent())
            throw new RuntimeException("标签不存在");
        tagRepository.delete(tagOptional.get());
    }

    /**
     * 列出用户所有标签
     *
     * @param userId
     * @return
     */
    public List<Tag> findByUserId(long userId) {
        log.info(String.format("userId: %d %s", userId, tagRepository.findByUserId(userId)));
        return tagRepository.findByUserId(userId);
    }

    /**
     * 给任务添加标签
     *
     * @param tagId
     * @param taskId
     */
    public void distribute(long tagId, long taskId) {
        Tag tag = tagRepository.findOne(tagId);
        Optional<Task> taskOption = taskService.getById(taskId);
        if (tag != null && taskOption.isPresent()) {
            TaskTag taskTag = new TaskTag();
            taskTag.setTagId(tagId);
            taskTag.setTaskId(taskId);
            log.info(String.format("entity to save: {}", taskTag));
            taskTagRepository.save(taskTag);
        } else {
            throw new RuntimeException("标签不存在或者任务不存在");
        }
    }

    /**
     * 删除任务与标签之间的关系
     *
     * @param tagId
     * @param taskId
     */
    public void undistribute(long tagId, long taskId) {
        Optional<TaskTag> taskTagOptional = taskTagRepository.findByTaskIdAndTagId(taskId, tagId);
        if (taskTagOptional.isPresent()) {
            taskTagRepository.delete(taskTagOptional.get());
        } else {
            throw new RuntimeException("标签不存在");
        }
    }

    /**
     * 查询给定任务下标签
     *
     * @param taskId
     * @return
     */
    public Iterable<Tag> listTagsForSpecificTask(long taskId) {
        List<TaskTag> taskTags = taskTagRepository.findByTaskId(taskId);
        log.info(String.format("find taskTag %s", taskTags));
        Iterable<Tag> tags = tagRepository.findAll(taskTags.stream().map(TaskTag::getId).collect(Collectors.toList()));
        for (Tag tag : tags) {
            log.info(String.format("this is %s", tag));
        }
        return tags;
    }


}
