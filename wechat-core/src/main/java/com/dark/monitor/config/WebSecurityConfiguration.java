package com.dark.monitor.config;


import com.dark.monitor.filter.CustomLoginFilter;
import com.dark.monitor.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomUserDetailsService userDetailsService;

	@Autowired SessionRegistry sessionRegistry = new SessionRegistryImpl();


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(new CustomPasswordEncoder());
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/swagger-resources/**", "/swagger-ui.html**","/api/ucm/**","/ws/**","/exchange1/**","/public/**");
		web.ignoring().antMatchers("/pad/apkDownload/**");
		web.ignoring().antMatchers("/websocketRfid/**");
		web.ignoring().antMatchers("/kaptcha");
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.err.println("自定义拦截器");
		String[] arr={"/template/getter","/websocketRfid/*","/pad/apkDownload/*","/business/consult","/attachment/findAttachment","/template/catalogue","/archive/archiveSync","/archive/queryRecordIdByOrgCodeAndIdCard","/attachment/uploadSynAttachment","/pad/*","/exchange/queryArchiveNeosoft"};
		http.authorizeRequests().antMatchers("/exchange/**").permitAll();
		//http.authorizeRequests().antMatchers("/exchange/queryArchiveNeosoft").authenticated();

		http.authorizeRequests().antMatchers(arr).permitAll()
				.anyRequest().authenticated()
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).maximumSessions(1).maxSessionsPreventsLogin(true).expiredSessionStrategy(new CustomExpiredSessionStrategy()).sessionRegistry(sessionRegistry)
			.and().sessionFixation().changeSessionId()
			.and().cors()
			.and().csrf().disable();
		http.x509().subjectPrincipalRegex("CN=(.*?)(?:,|$)");
		http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
		//http.addFilterBefore(captchaCodeFilter, UsernamePasswordAuthenticationFilter.class);
		http.logout().logoutUrl("/logout").logoutSuccessHandler(new CustomLogoutSuccessHandler()).invalidateHttpSession(true).deleteCookies("JSESSIONID");
		http.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler());
	}

	@Bean
	protected CustomLoginFilter loginFilter() throws Exception {
		CustomLoginFilter filter = new CustomLoginFilter();
		filter.setAuthenticationManager(authenticationManager());
		filter.setPostOnly(true);
		filter.setFilterProcessesUrl("/login");
		filter.setAuthenticationSuccessHandler(new CustomLoginSuccessHandler());
		filter.setAuthenticationFailureHandler(new CustomLoginFailHandler());
        return filter;
	}

	@Bean
	public SessionRegistry getSessionRegistry(){
		return sessionRegistry;
	}

	/**
	 * 注册Servlet Listener,用于发布Session的创建和销毁事件
	 * @return
	 */
	@Bean
	public ServletListenerRegistrationBean httpSessionEventPublisher() {
		ServletListenerRegistrationBean<HttpSessionEventPublisher> registration = new ServletListenerRegistrationBean<>();
		registration.setListener(new HttpSessionEventPublisher());
		return registration;
	}
}
