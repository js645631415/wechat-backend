package com.dark.monitor.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableCaching
@AutoConfigureAfter(CacheAutoConfiguration.class)
public class CachingConfig extends CachingConfigurerSupport {
	@Bean @Override
	public KeyGenerator keyGenerator() {
		return (target, method, params) -> {
			StringBuilder key = new StringBuilder();
			key.append(target.getClass().getName());
			key.append(".");
			key.append(method.getName());
			if(params.length != 0) {
				key.append(":");
				for(Object param : params) {
					if(null==param) param=new Object();
					key.append(param.getClass().getName());
					key.append("&");
					key.append(JSON.toJSONString(param));
					//key.append(Objects.hashCode(param));
					key.append("&");
				}
			}
			key.append("$$");
			return DigestUtils.sha256Hex(key.toString());
		};
	}
}
