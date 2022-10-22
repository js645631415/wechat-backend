package com.dark.monitor.controller.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;


@Slf4j
@Component
public abstract class BaseController {
    @Autowired protected HttpServletRequest request;
    @Autowired PrincipalUtil principalUtil;

    protected Principal getPrincipal() {
        return request.getUserPrincipal();
    }
    protected List<Long> getRoleId() {
        return principalUtil.getAccountIdByUserName(getPrincipal());
    }
    protected List<Long> getPermitShowOrgids() {
        return principalUtil.getPermitShowOrgids(getRoleId());
    }
    protected String getUserName() {
        return getPrincipal().getName();
    }
    protected Long getOrganizationId() {
        return  principalUtil.getOrganizationId(getPrincipal());
    }

}
