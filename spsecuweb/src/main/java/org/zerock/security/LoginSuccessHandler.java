package org.zerock.security;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

import lombok.extern.log4j.Log4j;

@Log4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		log.info("authentication: " + authentication);

		
		Enumeration<String> en = request.getSession().getAttributeNames();
		
		while(en.hasMoreElements()) {
			
			String key = en.nextElement();
			Object value = request.getSession().getAttribute(key);
			
			
			log.info("-----------------------------------");
			log.info(key);
			log.info(value);
			
		}
		
		DefaultSavedRequest dest = (DefaultSavedRequest)request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
		
		if(dest != null) {
		
			response.sendRedirect(dest.getRequestURI());
		}
		
		
	}

}
