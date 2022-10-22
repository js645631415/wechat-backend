package com.dark.monitor.entity.system;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class AccountOrganizationPK implements Serializable {
    private static final long serialVersionUID = 6453245078070144707L;
    /***/
    @ManyToOne @JoinColumn(name = "ACCOUNT_ID")
    private @Getter @Setter AccountEntity account;
    /***/
    @ManyToOne @JoinColumn(name = "ORGANIZTION_ID")
    private @Getter @Setter OrganizationEntity organization;

    protected AccountOrganizationPK() {
    }

    public AccountOrganizationPK(AccountEntity account, OrganizationEntity organization) {
        this.account = account;
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountOrganizationPK that = (AccountOrganizationPK) o;
        return Objects.equals(account, that.account) && Objects.equals(organization, that.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, organization);
    }
}
