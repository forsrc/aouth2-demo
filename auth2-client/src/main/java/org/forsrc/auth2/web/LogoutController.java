package org.forsrc.auth2.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LogoutController {

	@Value("${my-auth2.auth2-client.logoutUrl}")
	private String logoutUrl;

	@GetMapping("/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response,
			@RegisteredOAuth2AuthorizedClient("auth2-client-authorization-code") OAuth2AuthorizedClient authorizedClient) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
			return "redirect:" + logoutUrl;
		}
		return "redirect:/index?logout";
	}
}
