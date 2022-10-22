package com.dark.monitor.entity.terminal;

import com.dark.monitor.entity.system.AbstractAuditable;
import com.dark.monitor.entity.system.OrganizationEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TER_DEV")
public class TerminalDevEntity  extends AbstractAuditable<Long> {
    /**自助机名字*/
    @Column(name = "TER_NAME")
    private @Getter @Setter String terminalName;
    /**地址*/
    @Column(name = "TER_ADDRESS")
    private @Getter @Setter String terminalAdress;
    /**设备编号*/
    @Column(name = "TER_DEVNO")
    private @Getter @Setter String terminalDevNo;
    /***/
    @ManyToOne
    @JoinColumn(name = "ORGANIZTION_ID")
    private @Getter @Setter OrganizationEntity organization;
    /**是否处于黑名单*/
    @Column(name = "ACTIVE")
    private @Getter @Setter boolean active;
    /**逻辑删除*/
    @Column(name = "IS_DELETE")
    private @Getter @Setter boolean isdelete;

}
