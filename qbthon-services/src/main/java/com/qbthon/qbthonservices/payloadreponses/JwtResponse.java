package com.qbthon.qbthonservices.payloadreponses;

import java.util.List;

public class JwtResponse {

	String token;
	public JwtResponse(String jwt, String username, String email, List<String> roles) {
		// TODO Auto-generated constructor stub
		
		this.token = jwt;
		this.username = username;
		this.email =email;
		this.roles= roles;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	String username;
	String email;
	List<String> roles;
	
}
