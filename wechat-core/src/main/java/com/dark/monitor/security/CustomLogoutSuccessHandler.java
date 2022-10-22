package com.dark.monitor.security;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Slf4j
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		JSONObject object = new JSONObject();
		object.put("authenticated", false);
		response.getWriter().write(object.toString());
		response.getWriter().flush();
		//统计在线人数
		HttpSession session = request.getSession(true);
		if(session != null){
			session.invalidate();
		}

		String uname=authentication.getName();
		log.info(uname+"退出登录");
	}

}
