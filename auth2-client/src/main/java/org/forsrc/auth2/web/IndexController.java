package org.forsrc.auth2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {


	@GetMapping("/demo")
	public String demo() {
		return "demo";
	}
}
