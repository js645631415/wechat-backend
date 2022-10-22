package com.dark.monitor.repository.system;


import com.dark.monitor.entity.system.OrganizationResourcePK;
import com.dark.monitor.entity.system.OrgnazitonResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrganizationResourceRepository extends JpaRepository<OrgnazitonResourceEntity, OrganizationResourcePK> {


    @Query("select e from OrgnazitonResourceEntity e where e.id.organization.id in (:_ids)")
    List<OrgnazitonResourceEntity> findAllByGroupIdIn(@Param("_ids") List<Long> ids);

}

