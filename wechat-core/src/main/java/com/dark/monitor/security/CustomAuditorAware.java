package com.dark.monitor.security;


import com.dark.monitor.entity.system.AccountEntity;
import com.dark.monitor.repository.system.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


public class CustomAuditorAware implements AuditorAware<Long> {
	private final AccountRepository accountRepository;

	@Autowired
	public CustomAuditorAware(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Override public Optional<Long> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getPrincipal());
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		AccountEntity account = accountRepository.findByUsername(username);
		assert account.getId() != null;
		return Optional.of(account.getId());
	}
}
