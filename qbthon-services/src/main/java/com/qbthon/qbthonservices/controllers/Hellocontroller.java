package com.qbthon.qbthonservices.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class Hellocontroller {

	@RequestMapping(value = "/getpage", method = RequestMethod.GET)
	public ResponseEntity<Object> authenticate(HttpServletRequest request){
		
		return new ResponseEntity<>("hello", HttpStatus.OK);
	}
}
