package info.xsh.done.core.service;

import info.xsh.done.core.BaseComponent;
import info.xsh.done.core.controller.vo.TagVo;
import info.xsh.done.core.domain.Tag;
import info.xsh.done.core.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by xiaohuo on 16/12/7.
 */
@Service
public class TagService extends BaseComponent {
    @Autowired
    private TagRepository tagRepository;


    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag add(TagVo tagVo, long userId) {
        Optional<Tag> tagOptional = tagRepository.findByUserIdAndName(userId, tagVo.getName());
        if (tagOptional.isPresent()) {
            throw new RuntimeException("标签已存在");
        }
        Tag tag = convert(Tag.class, tagVo);
        tag.setUserId(userId);
        return this.save(tag);
    }

    public void delete(long userId,long tagId) {
        Optional<Tag> tagOptional = tagRepository.findByIdAndUserId(tagId,userId);
        if (!tagOptional.isPresent())
            throw new RuntimeException("标签不存在");
        tagRepository.delete(tagOptional.get());

    }
}
