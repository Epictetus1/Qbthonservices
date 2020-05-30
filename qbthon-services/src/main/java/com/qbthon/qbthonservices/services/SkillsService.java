package com.qbthon.qbthonservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.qbthon.qbthonservices.models.Skills;
import com.qbthon.qbthonservices.repository.SkillsRepository;


@Component
@Service
public class SkillsService {
	
	@Autowired
	  private SkillsRepository repository;


	public List<Skills> getAllSkills()  {
		// TODO Auto-generated method stub
		List<Skills> skills = repository.findAll();
	   
	    return skills;
	}

}
