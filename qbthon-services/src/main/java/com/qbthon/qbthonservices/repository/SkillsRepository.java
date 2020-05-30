package com.qbthon.qbthonservices.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.qbthon.qbthonservices.models.Skills;

@Repository
public interface SkillsRepository extends MongoRepository<Skills, String> {

	List<Skills> findAll();
}
