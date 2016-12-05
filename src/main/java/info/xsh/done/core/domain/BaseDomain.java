package info.xsh.done.core.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * Created by xiaohuo on 16/11/28.
 */
public abstract class BaseDomain {

    @Column(name = "created_at")
    protected LocalDateTime createAt;

    @Column(name = "updated_at")
    protected LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }


}
