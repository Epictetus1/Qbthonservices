package com.qbthon.qbthonservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qbthon.qbthonservices.models.Users;
import com.qbthon.qbthonservices.repository.UserRepository;
import com.qbthon.qbthonservices.securityservices.UserDetailsImpl;


@Service
public class MongoLoginService implements UserDetailsService {
  @Autowired
  private UserRepository repository;


public Users loadByUsername(String username)  {
	// TODO Auto-generated method stub
	Users user = repository.findByUsername(username);
	if(user ==null) {
		System.out.println("did not get user");
	}
   
    return user;
}

  @Override
  
  @Transactional 
  public UserDetails loadUserByUsername(String username) throws   UsernameNotFoundException 
  { 
	  Users user = repository.findByUsername(username);
	  System.out.println("came to userdetailsservice impl");
	  
  if(user ==null) {
	  System.out.println("user not there");
     throw new   UsernameNotFoundException("User Not Found with username: " + username); }
  
  return UserDetailsImpl.build(user); }
 
}