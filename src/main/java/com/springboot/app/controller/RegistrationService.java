package com.springboot.app.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.email.EmailSender;
import com.springboot.app.model.AppUser;
import com.springboot.app.model.AppUserRole;
import com.springboot.app.service.UserService;

@Service
public class RegistrationService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailSender emailSender;

	public String getUserCode() {
		Random random = new Random();
		int userCode = random.nextInt(9999);

		return String.format("%s", userCode);
	}

	public String register(RegistrationRequest request) {
		request.setUserCode(Long.parseLong(getUserCode()));

		String token = userService.signUpUser(new AppUser(request.getUserCode(), request.getUserName(), request.getEmail(),
				request.getPassword(), AppUserRole.USER)
		);
		String link = "http://localhost:8080/api/user/confirm/" + token;
		 emailSender.sendEmail(request.getEmail(), link);
		
		return ""; 
		
	}
	

	public String confirmToken(String token) {
		return userService.confirmToken(token);
	}

}
