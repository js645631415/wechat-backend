package com.dark.monitor.config;


import com.dark.monitor.security.CustomMethodSecurityExpressionHandler;
import com.dark.monitor.security.CustomPermissionEvaluator;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {
	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		CustomPermissionEvaluator evaluator = new CustomPermissionEvaluator();
		CustomMethodSecurityExpressionHandler handler = new CustomMethodSecurityExpressionHandler();
		handler.setEvaluator(evaluator);
		return handler;
	}
}
