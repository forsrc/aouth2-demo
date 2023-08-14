package org.forsrc.auth2.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

	@Value("${my-auth2.auth2-client.loginPage}")
	private String loginPage;

	@Value("${my-auth2.auth2-client.logoutUrl}")
	private String logoutUrl;
	
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/webjars/**");
	}

	// @formatter:off
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http, ClientRegistrationRepository clientRegistrationRepository) throws Exception {
		

		http
			.authorizeHttpRequests(authorize ->
				{
					authorize.requestMatchers("/actuator/**", "/", "/index").permitAll();
					authorize.anyRequest().authenticated();
				}
			)
			.logout(logout -> {
            	logout
            		.clearAuthentication(true)
            		.invalidateHttpSession(true)
            		.deleteCookies("JSESSIONID")
            		.logoutSuccessUrl("/")
            		.logoutUrl("/logout**")
            		.logoutSuccessHandler(logoutSuccessHandler())
            		.permitAll();
			})
			.oauth2Login(oauth2Login -> 
				oauth2Login.loginPage(loginPage))
			.oauth2Client(withDefaults());
		return http.build();
	}
	// @formatter:on

	private LogoutSuccessHandler logoutSuccessHandler() {
		return new LogoutSuccessHandler() {
			

			@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				final Authentication auth = SecurityContextHolder.getContext().getAuthentication();


				if (auth == null) {
					request.getRequestDispatcher("/?logout").forward(request, response);
					return;
				}

				new SecurityContextLogoutHandler().logout(request, response, auth);
				auth.setAuthenticated(false);
				SecurityContextHolder.clearContext();
				for (Cookie cookie : request.getCookies()) {
					String cookieName = cookie.getName();
					Cookie cookieToDelete = new Cookie(cookieName, null);
					cookieToDelete.setPath(request.getContextPath() + "/");
					cookieToDelete.setMaxAge(0);
					response.addCookie(cookieToDelete);
				}
				SecurityContextHolder.getContext().setAuthentication(null);
				request.getRequestDispatcher(logoutUrl).forward(request, response);
			}

		};
	}

}