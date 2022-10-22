package com.dark.monitor.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {
	Base64 base64;
    public CustomLoginFilter(){
		base64= new Base64();
	}
	@Resource
	private SessionRegistry sessionRegistry;
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if(request.getContentType().equals(MediaType.APPLICATION_JSON_UTF8_VALUE) || request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
			ObjectMapper mapper = new ObjectMapper();
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("", "");
			try(InputStream is = request.getInputStream()){
                CustomAuthenticationBean authenticationBean = mapper.readValue(is, CustomAuthenticationBean.class);
                //byte [] username=base64.decode(authenticationBean.getUsername());
                //byte [] password=base64.decode(authenticationBean.getPassword());
                token = new UsernamePasswordAuthenticationToken(authenticationBean.getUsername(),authenticationBean.getPassword());
            } catch(IOException e) {
				e.printStackTrace();
			} finally {
				setDetails(request, token);
			}
			Authentication authentication =this.getAuthenticationManager().authenticate(token);
			sessionRegistry.registerNewSession(request.getSession().getId(),token.getPrincipal());
			return authentication;
		} else {
			return super.attemptAuthentication(request, response);
		}
	}
}

class CustomAuthenticationBean {
	private @Getter @Setter String username;
	private @Getter @Setter String password;
}


