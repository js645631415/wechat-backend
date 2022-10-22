package com.dark.monitor.security;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {
	private final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
	private CustomPermissionEvaluator evaluator;

	public void setEvaluator(CustomPermissionEvaluator evaluator) {
		this.evaluator = evaluator;
	}

	@Override
	protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
		CustomMethodSecurityExpressionRoot root = new CustomMethodSecurityExpressionRoot(authentication);
		root.setThis(invocation.getThis());
		root.setEvaluator(evaluator);
		root.setTrustResolver(resolver);
		root.setRoleHierarchy(getRoleHierarchy());
		root.setDefaultRolePrefix(getDefaultRolePrefix());
		return root;
	}
}
