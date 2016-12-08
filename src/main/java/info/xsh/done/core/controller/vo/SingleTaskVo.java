package info.xsh.done.core.controller.vo;

import info.xsh.done.core.domain.YesOrNo;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by yangxueying on 2016/12/3.
 */
@Data
public class SingleTaskVo {

    private Long id;

    @NotEmpty
    private String name; // 任务名称

    private String detail; // 详情

    @NotNull
    private Long userId; //用户id

    @NotNull
    private YesOrNo isAchieved = YesOrNo.NO;

}
