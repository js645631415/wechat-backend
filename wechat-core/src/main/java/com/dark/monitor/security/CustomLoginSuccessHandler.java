package com.dark.monitor.security;

import com.alibaba.fastjson.JSONObject;
import com.dark.monitor.listener.SessionListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		// 允许跨域
		response.setHeader("Access-Control-Allow-Origin", "*");
		// 允许自定义请求头token(允许head跨域)
		response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
		if (request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE) || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)||request.getContentType().equals(MediaType.APPLICATION_FORM_URLENCODED_VALUE)) {
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			JSONObject object = new JSONObject();
			object.put("authenticated", true);
			object.put("success", true);
			object.put("status", 200);
			object.put("authority", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
			object.put("profile", authentication.getName());
			//统计在线人数
			AtomicInteger userCount = SessionListener.userCount;
			object.put("online", userCount);
			response.getWriter().write(String.valueOf(object));
			response.getWriter().flush();
			log.info("登录成功");
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}

}
