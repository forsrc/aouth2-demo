package org.forsrc.auth2.controller;

import java.io.IOException;
import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LogoutController {

    @GetMapping("/oauth/logout")
    // @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> user(HttpServletRequest request, HttpServletResponse response, Principal principal,
                                     String referer) {
        String user = principal == null ? "NO USER to logout" : principal.getName();
        new SecurityContextLogoutHandler().logout(request, null, null);
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

        try {
            redirectStrategy.sendRedirect(request, response, referer != null ? referer : request.getHeader("referer"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-> logout: " + principal);
        return ResponseEntity.ok().header("logout_user", user).build();
    }

}