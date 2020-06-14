package com.reportservice.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


import com.reportservice.models.EventDetails;



@Repository
public interface ReportsRepository extends MongoRepository<EventDetails, String>{
	

	
	List<EventDetails> findAll();
	 
	
	
	
}
