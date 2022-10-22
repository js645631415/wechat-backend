package com.dark.monitor.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;

public class CustomExpiredSessionStrategy implements SessionInformationExpiredStrategy {
	private ObjectMapper objectMapper= new ObjectMapper();
	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
		HashMap<String, Object> map = new HashMap<>();
		map.put("success", false);
		map.put("message","被迫下线！您的账户在另一台机器上登录或会话已经超时。");
		map.put("errorMessage","被迫下线！您的账户在另一台机器上登录或会话已经超时。");
		String json = objectMapper.writeValueAsString(map);
		sessionInformationExpiredEvent.getResponse().setContentType("application/json;charset=utf-8");
		sessionInformationExpiredEvent.getResponse().setStatus(401);
		sessionInformationExpiredEvent.getResponse().getWriter().write(json);
	}
}
