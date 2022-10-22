package com.dark.monitor.repository.terminal;

import com.dark.monitor.entity.terminal.TerminalLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminalLogRepository extends JpaRepository<TerminalLogEntity,Long >, JpaSpecificationExecutor<TerminalLogEntity> {
}
