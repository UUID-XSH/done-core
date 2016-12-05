package info.xsh.done.core.controller.vo;

import info.xsh.done.core.domain.Project;
import lombok.Data;

/**
 * Created by xiaohuo on 16/11/30.
 */
@Data
public class TaskVo {
    private Long id;
    private String name; // 任务名称
    private String detail; // 详情
    private Long projectId; //项目id
    private Project.YesOrNo isAchieved;
}
