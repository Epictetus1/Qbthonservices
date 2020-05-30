package com.qbthon.qbthonservices.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qbthon.qbthonservices.models.Skills;
import com.qbthon.qbthonservices.models.Users;
import com.qbthon.qbthonservices.services.MongoLoginService;
import com.qbthon.qbthonservices.services.SkillsService;

@CrossOrigin
@RestController
public class SkillsController {
	@Autowired
	 
	SkillsService skillsService;
	@RequestMapping(value = "/getskills", method = RequestMethod.GET,headers = "Accept=application/json",produces = "application/json")
	public ResponseEntity<Object> getSkills(HttpServletRequest request) {
		System.out.println("got skills get request");
	List<Skills> list = skillsService.getAllSkills();
		if(list == null) {
			return new ResponseEntity<>("some error",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
		
	}
	
}
