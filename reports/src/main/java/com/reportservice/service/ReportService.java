package com.reportservice.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.reportservice.models.EventDetails;
import com.reportservice.repository.ReportsRepository;

@Component
@Service
public class ReportService {
	
	@Autowired
	ReportsRepository reportsRepository;
	
	
	public List<EventDetails> getApprovedVsRejectedQuestion(){
		return reportsRepository.findAll();
	}
}
