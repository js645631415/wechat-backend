package com.dark.monitor.repository.system;


import com.dark.monitor.entity.system.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {
    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
    @Query("select coalesce(max(e.sort),0)+1 from ResourceEntity e where e.parent is null")
    int next();

    @Transactional(propagation = Propagation.NOT_SUPPORTED, rollbackFor = Exception.class)
    @Query("select coalesce(max(e.sort),0)+1 from ResourceEntity e where e.parent=:parent")
    int next(@Param("parent") ResourceEntity parent);

    @Query("select e from ResourceEntity e order by e.sortPath asc")
    @Override List<ResourceEntity> findAll();
}
