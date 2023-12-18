package com.pariksha.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pariksha.config.JwtUtils;
import com.pariksha.entity.JwtRequest;
import com.pariksha.entity.JwtResponse;
import com.pariksha.entity.User;
import com.pariksha.service.impl.UserDetailsServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl detailsServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	
	//generate token
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		try {
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		}catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User not found");
		}
		
		UserDetails details = this.detailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
		
		String token = this.jwtUtils.generateToken(details);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void authenticate(String username, String password) throws Exception 
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}catch(DisabledException e) 
		{
			throw new Exception("User Disabled " + e.getMessage());
		}catch (BadCredentialsException e) {
			throw new Exception("Invalid Credentials " + e.getMessage());
		}
		
	}
	
	//returns the details of current user
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) 
	{
		return (User) this.detailsServiceImpl.loadUserByUsername(principal.getName());
	}

}
