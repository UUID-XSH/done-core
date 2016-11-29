package info.xsh.down.core.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by xiaohuo on 16/11/28.
 */
@Data
@Entity
@Table(name = "project")
public class Project {
	@Id
	@Column(name = "id")
	private long id; // 项目id
	@Column(name = "name")
	private String name; // 项目名称
	@Column(name = "create_time")
	private String createTime; // 创建时间
	@Column(name = "achieved")
	@Enumerated(EnumType.STRING)
	private YesOrNo isAchieved; // 是否完成
	@Column(name = "archived")
	private YesOrNo isArchived; // 是否归档
	@Column(name = "user_id")
	private int userId;

	enum YesOrNo {
		YES, NO
	}
}
