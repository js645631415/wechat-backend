package com.dark.monitor.entity.system;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "SYS_ORGANIZATION_RESOURCE")
public class OrgnazitonResourceEntity extends AbstractAuditable<OrganizationResourcePK> {
    /** 激活状态 */
    @Column(name = "ACTIVE")
    private @Getter @Setter boolean active;

    protected OrgnazitonResourceEntity() {
        this.active = true;
    }

    public OrgnazitonResourceEntity(OrganizationEntity organizationEntity, ResourceEntity resource) {
        this.id = new OrganizationResourcePK(organizationEntity, resource);
    }


}
