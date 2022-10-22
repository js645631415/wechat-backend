package com.dark.monitor.repository.system;

import com.dark.monitor.entity.system.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountOrgnazitionRepository extends JpaRepository<AccountOrganizationEntity,AccountOrganizationPK > {
    @Query("select e from AccountOrganizationEntity e where e.id.account = :_account")
    List<AccountOrganizationEntity> findAllRolesByAccount(@Param("_account") AccountEntity account);
}
