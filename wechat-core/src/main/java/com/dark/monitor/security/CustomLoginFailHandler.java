package com.dark.monitor.security;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLoginFailHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		JSONObject object = new JSONObject();
		object.put("authenticated", false);
		object.put("success", false);
		object.put("status", 401);
		object.put("message", exception.getMessage());
		response.getWriter().write(String.valueOf(object));
	}
}
