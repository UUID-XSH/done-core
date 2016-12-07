package info.xsh.done.core.controller.vo;

import info.xsh.done.core.domain.YesOrNo;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by xiaohuo on 16/11/29.
 */
@Data
public class ProjectVo {

    private Long id;

    @NotEmpty
    private String name;

    private LocalDateTime createTime;

    private YesOrNo isAchieved;

    private YesOrNo isArchived; // 是否归档

    @NotNull
    private Long userId;

    private List<TaskVo> taskVos;
}
