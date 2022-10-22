package com.dark.monitor.repository.terminal;

import com.dark.monitor.entity.terminal.TerminalDevEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TerminalDevRepository extends JpaRepository<TerminalDevEntity,Long >, JpaSpecificationExecutor<TerminalDevEntity> {
    @Modifying
    @Transactional
    @Query("update TerminalDevEntity e set e.isdelete=1 where  e.id in :_ids")
    void deleteAllByIds( @Param(value = "_ids") List<Long> _ids);
}
