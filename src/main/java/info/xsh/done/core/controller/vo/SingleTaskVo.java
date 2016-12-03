package info.xsh.done.core.controller.vo;

import info.xsh.done.core.domain.Project;
import lombok.Data;

/**
 * Created by yangxueying on 2016/12/3.
 */
@Data
public class SingleTaskVo {
    private long id;
    private String name; // 任务名称
    private String detail; // 详情
    private long userId; //用户id
    private Project.YesOrNo isAchieved;
    private Project.YesOrNo isFinal;
}
