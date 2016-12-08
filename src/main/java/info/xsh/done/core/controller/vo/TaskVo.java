package info.xsh.done.core.controller.vo;

import info.xsh.done.core.domain.YesOrNo;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by xiaohuo on 16/11/30.
 */
@Data
public class TaskVo {

    private Long id;

    @NotEmpty
    private String name; // 任务名称

    private String detail; // 详情

    @NotNull
    private Long projectId; //项目id

    @NotNull
    private YesOrNo isAchieved = YesOrNo.NO;
}
