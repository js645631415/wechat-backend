package com.dark.monitor.entity.terminal;

import com.dark.monitor.entity.system.AbstractAuditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TER_LOG")
public class TerminalLogEntity extends AbstractAuditable<Long> {
    /**自助机设备*/
    @ManyToOne
    @JoinColumn(name = "DEV_ID")
    private @Getter @Setter TerminalDevEntity terminalDevEntity;
    /**业务类型*/
    @ManyToOne
    @JoinColumn(name = "BIZ_TYPE_ID")
    private @Getter @Setter TerBizType terBizType;
    /**查询人名字*/
    @Column(name = "BIZ_NAME")
    private @Getter @Setter String userName;
    /**查询人身份证号*/
    @Column(name = "BIZ_IDCAED")
    private @Getter @Setter String idCard;


}
