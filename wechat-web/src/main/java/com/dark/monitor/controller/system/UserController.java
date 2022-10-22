package com.dark.monitor.controller.system;

import com.dark.monitor.controller.base.BaseController;
import com.dark.monitor.entity.system.AccountEntity;
import com.dark.monitor.json.Response;
import com.dark.monitor.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired UserService userService;
    @GetMapping("/getInfo")
    public Response getInfo( ){
        Response ajax = Response.Success();
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       
        AccountEntity user=userService.getUserEntityByUserName(getUserName());
        List<Long> roleId = getRoleId();
        ajax.put("user", user);
        ajax.put("roles", roleId);
        ajax.put("permissions", principal.getAuthorities());
        return ajax;
    }
    @PostMapping("/regUser")
    @PreAuthorize("hasPermission('system:*')")
    public Response regUser( AccountEntity accountEntity){
        try {
            userService.saveUserAndOrg(accountEntity,getOrganizationId());
        } catch (Exception e){
            e.printStackTrace();
            return Response.Error();
        }
        return Response.Success();
    }
    @PostMapping("/test")
    @PreAuthorize("hasPermission('all:*')")
    public Response test( AccountEntity accountEntity){
        return Response.Success();
    }
}
