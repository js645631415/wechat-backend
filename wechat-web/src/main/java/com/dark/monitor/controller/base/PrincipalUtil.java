package com.dark.monitor.controller.base;

import com.dark.monitor.mapper.system.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;

@Component
public class PrincipalUtil {
    @Autowired private AccountMapper accountMapper;
    public Long getOrganizationId(Principal principal) {
        String username = principal.getName();
        return accountMapper.findOrgIdByUserName(username);
    }
    public List<Long> getAccountIdByUserName(Principal principal) {
        String username = principal.getName();
        return accountMapper.findRoleIdByUserName(username);
    }

    public List<Long> getPermitShowOrgids(List<Long> roleId) {
       return accountMapper.getPermitShowOrgids(roleId);
    }
}
