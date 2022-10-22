package com.dark.monitor.config;

import com.dark.monitor.listener.SessionListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author xichengxml
 * @date 2019/2/15 14:42
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer{


    @Bean
    public ServletListenerRegistrationBean getListener() {
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new SessionListener());
        return servletListenerRegistrationBean;
    }

}

