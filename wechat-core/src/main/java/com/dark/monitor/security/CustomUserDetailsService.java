package com.dark.monitor.security;


import com.dark.monitor.entity.system.AccountEntity;
import com.dark.monitor.entity.system.AccountOrganizationEntity;
import com.dark.monitor.entity.system.OrganizationEntity;
import com.dark.monitor.repository.system.AccountOrgnazitionRepository;
import com.dark.monitor.repository.system.AccountRepository;
import com.dark.monitor.repository.system.OrganizationResourceRepository;
import com.dark.monitor.repository.system.ResourceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired private RequestMappingHandlerMapping handlerMapping;
	@Autowired private SessionRegistry sessionRegistry;
	@Autowired private AccountRepository accountRepository;
	@Autowired private ResourceRepository resourceRepository;
	@Autowired private AccountOrgnazitionRepository accountOrgnazitionRepository;
	@Autowired private OrganizationResourceRepository organizationResourceRepository;
	@Override public UserDetails loadUserByUsername(String username) throws AuthenticationException {
		log.debug("loadUserByUsername::");
		boolean tag = true;
		Set<CustomGrantedPermissionAuthority> authorities = new HashSet<>();
		AccountEntity entity = accountRepository.findByUsername(username);
		if(null == entity) throw new BadCredentialsException("用户不存在!");
		if(!entity.isActive()) throw new BadCredentialsException("用户已禁用!");
		if(entity.getExpireDate()!=null && entity.getExpireDate().before(new Date())) throw new BadCredentialsException("用户已过期!");
		List<SessionInformation> sessionsInfo = sessionRegistry.getAllSessions(entity.getUsername(), false);
		if(null != sessionsInfo && sessionsInfo.size() > 0) {
			for(SessionInformation sessionInformation : sessionsInfo) {
				//当前session失效
				sessionInformation.expireNow();
			}
		}
		if(tag && "system".equals(username)) {
			Set<String> set = new HashSet<>();
			handlerMapping.getHandlerMethods().values().stream()
					.filter(item -> item.getMethod().isAnnotationPresent(PreAuthorize.class))
					.map(item -> item.getMethod().getAnnotation(PreAuthorize.class).value())
					.forEach(annotation -> {
						Matcher matcher = Pattern.compile("(?<=\').*?(?=\')").matcher(annotation);
						if(matcher.find()) set.add(matcher.group(0));
					});
			resourceRepository.findAll().forEach(resource -> set.add(resource.getPermission()));
			set.forEach(item -> authorities.add(new CustomGrantedPermissionAuthority(item)));
		} else {
			authorities.add(new CustomGrantedPermissionAuthority("*"));
			//List<Long> rids = groupAccountRepository.findAllRolesByAccount(entity).stream().map(e -> e.getGroup().getId()).collect(Collectors.toList());
			List<Long> roleId=new ArrayList<>();
			/**查询用户所属的角色*/
			List<AccountOrganizationEntity> allRoles = accountOrgnazitionRepository.findAllRolesByAccount(entity);
			allRoles.stream().forEach(item->{
				if (item.getId().getOrganization().equals(Long.valueOf(OrganizationEntity.OrganizationEnum.角色.getCode()))){
					roleId.add(item.getId().getOrganization().getId());
				}
			});

			organizationResourceRepository.findAllByGroupIdIn(roleId).forEach(item ->
					authorities.add(new CustomGrantedPermissionAuthority(item.getId().getResource().getPermission()))
			);
		}
			//List<Long> rids = groupAccountRepository.findAllRolesByAccount(entity).stream().map(e -> e.getGroup().getId()).collect(Collectors.toList());
		List<Long> rids=new ArrayList<>();

		return new User(entity.getUsername(), entity.getPassword(), authorities);
	}
}
