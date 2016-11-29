package info.xsh.down.core.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by xiaohuo on 16/11/28.
 */
@Entity
@Table(name = "task")
@Data
public class Task {
	@Id
	@Column(name = "id")
	private long id;
	@Column(name = "name")
	private String name; // 任务名称
	@Column(name = "detail")
	private String detail; // 详情
	@Column(name = "project_id")
	private long projectId;

}
