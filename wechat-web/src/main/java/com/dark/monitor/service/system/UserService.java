package com.dark.monitor.service.system;

import com.dark.monitor.entity.system.AccountEntity;
import com.dark.monitor.entity.system.AccountOrganizationEntity;
import com.dark.monitor.repository.system.AccountOrgnazitionRepository;
import com.dark.monitor.repository.system.AccountRepository;
import com.dark.monitor.repository.system.OrgnazitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired private AccountRepository accountRepository;
    @Autowired private OrgnazitionRepository orgnazitionRepository;
    @Autowired private AccountOrgnazitionRepository accountOrgnazitionRepository;
    @Transient()
    @Transactional
    public void saveUserAndOrg(AccountEntity accountEntity, Long organizationId) {
        AccountEntity saveAccount = accountRepository.save(accountEntity);
        accountOrgnazitionRepository.save(new AccountOrganizationEntity(saveAccount,orgnazitionRepository.getOne(organizationId)));
    }

    public AccountEntity getUserEntityByUserName(String userName) {
      return   accountRepository.findByUsername(userName);
    }
}
