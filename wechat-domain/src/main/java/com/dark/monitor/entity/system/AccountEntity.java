package com.dark.monitor.entity.system;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;



@Entity
@Table(name = "SYS_ACCOUNT")
public class AccountEntity extends AbstractAuditable<Long> {

    /***/
    @Column(name = "USERNAME")
    private @Getter @Setter String username;

    /***/
    @Column(name = "EMPLOYNAME")
    private @Getter @Setter String employName;
    /***/
    @Column(name = "MOBILE")
    private @Getter @Setter String mobile;
    /***/
    @Column(name = "PASSWORD")
    private @Getter @Setter String password;
    /***/
    @Column(name = "ACTIVE")
    private @Getter @Setter boolean active;
    /***/
    @Column(name = "EXPIRE_DATE")
    private @Getter @Setter Date expireDate;



}

