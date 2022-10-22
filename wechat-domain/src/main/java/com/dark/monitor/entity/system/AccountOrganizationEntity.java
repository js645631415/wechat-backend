package com.dark.monitor.entity.system;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "account_organization")
public class AccountOrganizationEntity extends AbstractAuditable<AccountOrganizationPK> {


    public AccountOrganizationEntity(AccountEntity account, OrganizationEntity organization) {
        this();
        this.id = new AccountOrganizationPK(account, organization);
    }

    public AccountOrganizationEntity() {

    }
}
