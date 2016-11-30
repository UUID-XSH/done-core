package info.xsh.done.core.controller.vo;

import info.xsh.done.core.domain.Project;
import lombok.Data;
import org.hibernate.type.YesNoType;

import java.time.LocalDateTime;

/**
 * Created by xiaohuo on 16/11/29.
 */
@Data
public class ProjectVo {
	private long id;
	private String name;
	private LocalDateTime createTime;
	private Project.YesOrNo isAchieved;
	private Project.YesOrNo isArchived; // 是否归档
	private long userId;
}
