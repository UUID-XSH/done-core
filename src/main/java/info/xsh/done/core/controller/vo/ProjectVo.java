package info.xsh.done.core.controller.vo;

import info.xsh.done.core.domain.Project;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by xiaohuo on 16/11/29.
 */
@Data
public class ProjectVo {
	private Long id;
	private String name;
	private LocalDateTime createTime;
	private Project.YesOrNo isAchieved;
	private Project.YesOrNo isArchived; // 是否归档
	private Long userId;
	private List<TaskVo> taskVos;
}
