package com.dark.monitor.entity.system;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class OrganizationResourcePK implements Serializable {
    private static final long serialVersionUID = 8626334550165142915L;
    /***/
    @ManyToOne @JoinColumn(name = "ORG_ID")
    private @Getter @Setter OrganizationEntity organization;
    /***/
    @ManyToOne @JoinColumn(name = "RESOURCE_ID")
    private @Getter @Setter ResourceEntity resource;

    protected OrganizationResourcePK() {
    }

    public OrganizationResourcePK(OrganizationEntity organization, ResourceEntity resource) {
        this.organization = organization;
        this.resource = resource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationResourcePK that = (OrganizationResourcePK) o;
        return Objects.equals(organization, that.organization) && Objects.equals(resource, that.resource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organization, resource);
    }
}
