package com.springboot.app.service;


import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.model.AppUser;
import com.springboot.app.repository.UserRepository;
import com.springboot.app.token.ConfirmationToken;
import com.springboot.app.token.ConfirmationTokenService;


@Service
public class UserService implements UserDetailsService{
	
   @Autowired
	private UserRepository userRepository;

	@Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
	private ConfirmationTokenService tokenService;
    
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository.findByEmail(email);
	}
	
	public String signUpUser(AppUser appUser) {
		boolean userExists = userRepository.findByEmail(appUser.getUsername()) != null;
	
		if(userExists) {
			throw new IllegalStateException("User name already exists!!");
		}
		String password = bcryptPasswordEncoder.encode(appUser.getPassword());
		appUser.setPassword(password);
		
		 userRepository.save(appUser);
		
		String token = UUID.randomUUID().toString();
		ConfirmationToken confirmationToken = new ConfirmationToken(
				token, 
				LocalDateTime.now(), 
				LocalDateTime.now().plusMinutes(15), 
				appUser);
		
		tokenService.saveConfirmationToken(confirmationToken);
		
		return token;
	}
	
	@Transactional
	public String confirmToken(String token) {
		
		ConfirmationToken confirmationToken = tokenService.getToken(token);
		
		if(confirmationToken.getConfirmedAt() != null) {
			return "Email already confirmed!!";
		}
		
		LocalDateTime expired = confirmationToken.getExpiresAt();
		
		if(expired.isBefore(LocalDateTime.now())) {
			return "Token already Expired!!";
		}
		
		tokenService.confirmedAt(token);
	   enableAppUser(confirmationToken.getAppUser().getEmail());
		
		return "Confirmed";
		
	}
	
	public boolean enableAppUser(String email) {
		AppUser user = userRepository.findByEmail(email);
		 user.setEnabled(true);
		
		 return true;
	}
	
}
