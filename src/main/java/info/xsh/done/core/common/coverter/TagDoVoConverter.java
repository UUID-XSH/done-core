package info.xsh.done.core.common.coverter;

import info.xsh.done.core.controller.vo.TagVo;
import info.xsh.done.core.domain.Tag;
import info.yannxia.java.chameleon.annonation.Convertor;
import org.springframework.stereotype.Component;

/**
 * Created by xiaohuo on 16/12/7.
 */
@Component
public class TagDoVoConverter {

    @Convertor
    public Tag voToDo(TagVo tagVo) {
        Tag tagDo = new Tag();
        tagDo.setName(tagVo.getName());
        return tagDo;
    }

    @Convertor
    public TagVo doToVo(Tag tagDo) {
        TagVo tagVo = new TagVo();
        tagVo.setName(tagDo.getName());
        tagVo.setId(tagDo.getId());
        return tagVo;
    }

}
