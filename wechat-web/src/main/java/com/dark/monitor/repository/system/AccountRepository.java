package com.dark.monitor.repository.system;


import com.dark.monitor.entity.system.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long>, JpaSpecificationExecutor<AccountEntity> {
    @Query("select e from AccountEntity e")
    Page<AccountEntity> page(Pageable pageable);


    AccountEntity findByUsername(String username);

    AccountEntity findByMobile(String mobile);

    boolean existsByUsername(String username);

    boolean existsByIdNotAndUsername(Long id, String username);
}

