package com.dark.monitor.security;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class CustomMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {
	private Object filterObject;
	private Object returnObject;
	private Object target;
	private CustomPermissionEvaluator evaluator;

	CustomMethodSecurityExpressionRoot(Authentication authentication) {
		super(authentication);
	}

	void setEvaluator(CustomPermissionEvaluator evaluator) {
		this.evaluator = evaluator;
	}

	public boolean hasPermission(Object permission) {
		return evaluator.hasPermission(authentication, permission);
	}

	public void setFilterObject(Object filterObject) {
		this.filterObject = filterObject;
	}

	public Object getFilterObject() {
		return filterObject;
	}

	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}

	public Object getReturnObject() {
		return returnObject;
	}

	void setThis(Object target) {
		this.target = target;
	}

	public Object getThis() {
		return target;
	}
}
