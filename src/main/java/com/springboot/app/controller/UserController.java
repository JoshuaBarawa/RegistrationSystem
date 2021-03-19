package com.springboot.app.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/user/")
public class UserController {
	
	@Autowired
	private RegistrationService registrationService;
     
	@PostMapping("/registration")
	public String registration(@RequestBody RegistrationRequest request) {
		 registrationService.register(request);
		 
		 return"A link has been sent to your email......click to confirm your account";
	}
	
	@GetMapping("/confirm/{token}")
	public String confirmToken(@PathVariable String token) {
		return registrationService.confirmToken(token);
	}
	

}
