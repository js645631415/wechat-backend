package com.dark.monitor.entity.terminal;

import com.dark.monitor.entity.system.AbstractAuditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TER_BIZ_TYPE")
public class TerBizType extends AbstractAuditable<Long> {
    /**自助机名字*/
    @Column(name = "BIZ_NAME")
    private @Getter @Setter String bussinessName;
}
