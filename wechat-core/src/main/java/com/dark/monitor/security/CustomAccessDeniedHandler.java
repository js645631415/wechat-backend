package com.dark.monitor.security;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exception) throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		JSONObject object = new JSONObject();
		object.put("success", false);
		object.put("status", 403);
		object.put("message", "Opps! 您无访问权限");
		response.getWriter().write(String.valueOf(object));
	}
}
