package com.dark.monitor.repository.system;




import com.dark.monitor.entity.system.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface OrgnazitionRepository extends JpaRepository<OrganizationEntity, Long>, JpaSpecificationExecutor<OrganizationEntity> {
}

