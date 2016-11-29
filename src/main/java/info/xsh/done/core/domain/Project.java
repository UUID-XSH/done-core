package info.xsh.done.core.domain;

import lombok.Data;
import org.springframework.data.annotation.Persistent;
import sun.jvm.hotspot.oops.Instance;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

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
	private LocalDateTime createTime = LocalDateTime.now(); // 创建时间
	@Column(name = "achieved")
	@Enumerated(EnumType.STRING)
	private YesOrNo isAchieved = YesOrNo.NO; // 是否完成
	@Column(name = "archived")
	private YesOrNo isArchived = YesOrNo.NO; // 是否归档
	@Column(name = "user_id")
	private long userId;

	enum YesOrNo {
		YES, NO
	}
}
