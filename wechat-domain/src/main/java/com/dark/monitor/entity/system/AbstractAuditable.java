package com.dark.monitor.entity.system;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 具备审计的基础类
 *
 * @param <ID> ID，自增量
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditable<ID extends Serializable> implements Persistable<ID>, Serializable {
    /** ID */

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "ID")
    protected @Getter ID id;
    /** 创建主体 */
    @CreatedBy @Column(name = "CREATED_BY")
    protected @Getter @Setter Long createdBy;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @CreatedDate @Column(name = "CREATED_DATE")
    protected @Getter LocalDateTime createdDate;
    /** 最后修改主体 */
    @LastModifiedBy @Column(name = "MODIFIED_BY")
    protected @Getter @Setter Long lastModifiedBy;
    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @LastModifiedDate @Column(name = "MODIFIED_DATE")
    protected @Getter @Setter LocalDateTime lastModifiedDate;

    @Override @Transient
    public boolean isNew() {
        return null == id;
    }

}
