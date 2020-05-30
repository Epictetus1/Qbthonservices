package com.qbthon.qbthonservices.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.qbthon.qbthonservices.models.Users;


	@Repository
	public interface UserRepository extends MongoRepository<Users, String> {
		Users findByUsername(String username);
	}

