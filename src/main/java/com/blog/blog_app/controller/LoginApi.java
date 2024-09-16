package com.blog.blog_app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog_app.configuration.jwt.JwtTokenHelper;
import com.blog.blog_app.model.JwtAuthRequest;
import com.blog.blog_app.model.JwtAuthResponse;

@RestController
@RequestMapping("/api")
public class LoginApi {
private Logger logger = LoggerFactory.getLogger(LoginApi.class);

@Autowired
private AuthenticationManager manager;

@Autowired
private UserDetailsService userDetailService;

@Autowired
private JwtTokenHelper helper;


@PostMapping("/login")
public ResponseEntity<JwtAuthResponse> login(@RequestBody JwtAuthRequest request){
	this.doAuthenticate(request.getEmail(), request.getPassword());
	
	UserDetails userDetails = userDetailService.loadUserByUsername(request.getEmail());
	String token = this.helper.generateToken(userDetails);
	
	JwtAuthResponse response = new JwtAuthResponse();
	response.setToken(token);
	response.setUsername(userDetails.getUsername());
	
	return new ResponseEntity<>(response, HttpStatus.OK);
}


private void doAuthenticate(String email, String password) {
	UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
	
	try {
		manager.authenticate(authentication);
	}catch(BadCredentialsException e) {
		throw new BadCredentialsException("Invalid Username or password!");
	}
}
}
