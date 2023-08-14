package org.forsrc.auth2.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LogoutController {

	@Value("${my-auth2.auth2-client.logoutUrl}")
	private String logoutUrl;

	@GetMapping("/logout")
	public void logoutPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && !"anonymousUser".equals(auth.getPrincipal())) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			
			SecurityContextHolder.clearContext();
			response.setHeader("callbackUrl", request.getRequestURL().toString().replace(request.getRequestURI().toString(), "/"));
			response.sendRedirect(logoutUrl);
			return;
		}
		
		request.getRequestDispatcher("/?logout").forward(request, response);
	}
}
