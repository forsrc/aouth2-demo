package org.forsrc.auth2.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LogoutController {

	@GetMapping("/oauth/logout")
	// @PreAuthorize("isAuthenticated()")
	public ResponseEntity<Void> user(HttpServletRequest request, HttpServletResponse response, Principal principal,
			@RequestHeader(name = "Referer", required = false) String referer,
			@RequestParam(name = "callbackUrl", required = false) String callbackUrl) {
		String user = principal == null ? "NO USER to logout" : principal.getName();
		new SecurityContextLogoutHandler().logout(request, null, null);
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

		try {
			redirectStrategy.sendRedirect(request, response,
					callbackUrl != null ? callbackUrl : (referer != null ? referer : "/?logout"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("-> logout: " + principal);
		return ResponseEntity.ok().header("logout_user", user).build();
	}

}