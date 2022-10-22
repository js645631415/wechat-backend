package com.dark.monitor.entity.terminal.dto;

import com.dark.monitor.entity.system.OrganizationEntity;
import lombok.Getter;
import lombok.Setter;

public class TerminalDevDto {
    /**自助机名字*/
    private @Getter @Setter Long id;
    /**自助机名字*/
    private @Getter @Setter String terminalName;
    /**地址*/
    private @Getter @Setter String terminalAdress;
    /**设备编号*/
    private @Getter @Setter String terminalDevNo;
    /***/
    private @Getter @Setter OrganizationEntity organization;
    /***/
    private @Getter @Setter Long organizationId;
    /**是否处于黑名单*/
    private @Getter @Setter boolean active;
}
