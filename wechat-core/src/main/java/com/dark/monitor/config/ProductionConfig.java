package com.dark.monitor.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ApplicationContextUtil.class)
public class ProductionConfig {
}
