package org.forsrc.auth2.web;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {



	@GetMapping("/test")
	public Principal test(Principal principal) {
		return principal;
	}

}
