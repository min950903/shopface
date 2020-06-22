package kr.ac.sunmoon.shopface.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		String role = authentication.getAuthorities().toString();
		
		String targetUrl = "";
		if (role.contains("MEMBER")) {
			targetUrl = "/schedule";
		} else if (role.contains("BUSINESSMAN")) {
			targetUrl = "/timetable";
		} else if (role.contains("ADMIN")) {
			targetUrl = "/member";
		}
		
		getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}
}
