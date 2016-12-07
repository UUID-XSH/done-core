package info.xsh.done.core.controller;

import info.xsh.done.core.BaseComponent;
import info.xsh.done.core.controller.vo.TagVo;
import info.xsh.done.core.domain.Tag;
import info.xsh.done.core.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xiaohuo on 16/12/6.
 */
@RestController
@RequestMapping(value = "/api/v1.0/users/{userId}", produces = "application/json")
public class TagController extends BaseComponent {

    @Autowired
    private TagService tagService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/tags", method = RequestMethod.POST)
    public TagVo add(@RequestBody TagVo tagVo, @PathVariable long userId) {
        Tag tagDo = tagService.add(tagVo, userId);
        return convert(TagVo.class, tagDo);
    }

    @RequestMapping(value = "/tags/{tagId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long userId, @PathVariable long tagId) {
        tagService.delete(userId,tagId);
    }

}
