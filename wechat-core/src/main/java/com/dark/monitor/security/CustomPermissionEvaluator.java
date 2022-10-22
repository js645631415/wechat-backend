package com.dark.monitor.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;

/**
 * GrantedAuthority-授权
 * authentication-身份验证
 * getAuthorities 获取权限
 * 在接口方法上面增加了PreAuthorize注解后还需要实现自己的PermissionEvaluator，Spring Security将在hasPermission()方法中对当前登录用户正在访问的资源及其对资源进行的操作进行合法性校验。
 * 注意，这里targetDomainObject即是我们之前定义的resourceId，而permission即为privilege，在校验时要将其组合为和UserDetailsService中存储格式一致的格式
 */
@Service
public class CustomPermissionEvaluator implements PermissionEvaluator {
	public boolean hasPermission(Authentication authentication, Object permission) {
		boolean tag = true;
		User user = (User) authentication.getPrincipal();
		if(tag && user.getUsername().equals("system")) return true;
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();//TODO
		for(GrantedAuthority authority : authorities) if(authority.getAuthority().equals(permission)) return true;
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		return hasPermission(authentication, permission);
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
		return hasPermission(authentication, permission);
	}
}
