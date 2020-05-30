package com.qbthon.qbthonservices.controllers;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

import com.qbthon.qbthonservices.jwt.JwtUtils;
import com.qbthon.qbthonservices.models.Users;
import com.qbthon.qbthonservices.payloadreponses.JwtResponse;
import com.qbthon.qbthonservices.securityservices.UserDetailsImpl;
import com.qbthon.qbthonservices.services.MongoLoginService;

@CrossOrigin
@RestController
public class LoginController {

	@Autowired
	 
	 MongoLoginService mongoLoginService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;

	@RequestMapping(value = "/login/{username}/{password}", method = RequestMethod.POST,headers = "Accept=application/json",produces = "application/json")
	public ResponseEntity<Object> authenticate(HttpServletRequest request, @PathVariable("username") String userName,@PathVariable("password") String password) {
		System.out.println("got request");
		//System.out.println("password got fromdb is "+mongoLoginService.loadUserByUsername(userName).getPassword());
		/*
		 * Users users = mongoLoginService.loadByUsername(userName);
		 * if(password.equals(users.getPassword())) { return new ResponseEntity<>(users,
		 * HttpStatus.OK); } return new
		 * ResponseEntity<>("The userName or password is wrong",
		 * HttpStatus.UNAUTHORIZED);
		 */
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userName, password));
		
		System.out.println("step1 done");

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
		
	}
}