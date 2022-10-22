package com.dark.monitor.entity.system;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ROLE_ORGANIZATION")
public  class Role_Orgnazition  {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;
    /** 名称 */
    @Column(name = "ROLE_ID")
    protected @Getter @Setter Long roleId;
    /** 状态 */
    @Column(name = "ORGNAZITION_ID")
    protected @Getter @Setter Long status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
