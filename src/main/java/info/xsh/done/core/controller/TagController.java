package info.xsh.done.core.controller;

import info.xsh.done.core.BaseComponent;
import info.xsh.done.core.controller.vo.TagVo;
import info.xsh.done.core.domain.Tag;
import info.xsh.done.core.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiaohuo on 16/12/6.
 */
@RestController
@RequestMapping(value = "/api/v1.0/users/{userId}", produces = "application/json")
public class TagController extends BaseComponent {

    @Autowired
    private TagService tagService;

    /**
     * 为某个用户创建标签
     *
     * @param tagVo
     * @param userId
     * @return
     */
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/tags", method = RequestMethod.POST)
    public TagVo add(@RequestBody TagVo tagVo, @PathVariable long userId) {
        Tag tagDo = tagService.add(tagVo, userId);
        return convert(TagVo.class, tagDo);
    }

    /**
     * 列出该用户下所有标签
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public List<TagVo> findByUserId(@PathVariable long userId) {
        List<Tag> tags = tagService.findByUserId(userId);
        return tags.stream().map(tag -> convert(TagVo.class, tag)).collect(Collectors.toList());
    }

    /**
     * 删除标签
     *
     * @param userId
     * @param tagId
     */
    @RequestMapping(value = "/tags/{tagId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long userId, @PathVariable long tagId) {
        tagService.delete(userId, tagId);
    }

    /**
     * 给该用户下某个任务添加标签
     * TODO:用户是否可以操作任务
     *
     * @param tagId
     * @param taskId
     */
    @RequestMapping(value = "/tags/{tagId}/tasks/{taskId}", method = RequestMethod.PUT)
    public void distribute(@PathVariable long tagId, @PathVariable long taskId) {
        tagService.distribute(tagId, taskId);
    }

}
