package info.xsh.done.core.controller.vo;

import lombok.Data;

/**
 * Created by xiaohuo on 16/11/30.
 */
@Data
public class TaskVo {
    private long id;
    private String name; // 任务名称
    private String detail; // 详情
    private long projectId; //项目id
}
