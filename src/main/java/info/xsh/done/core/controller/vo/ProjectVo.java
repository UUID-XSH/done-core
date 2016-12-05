package info.xsh.done.core.controller.vo;

import java.time.LocalDateTime;
import java.util.List;

import info.xsh.done.core.domain.YesOrNo;
import lombok.Data;

/**
 * Created by xiaohuo on 16/11/29.
 */
@Data
public class ProjectVo {
	private long id;
	private String name;
	private LocalDateTime createTime;
	private YesOrNo isAchieved;
	private YesOrNo isArchived; // 是否归档
	private long userId;
	private List<TaskVo> taskVos;
}
