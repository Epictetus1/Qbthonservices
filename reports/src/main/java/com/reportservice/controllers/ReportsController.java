package com.reportservice.controllers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reportservice.models.EventDetails;
import com.reportservice.service.ReportService;

@CrossOrigin
@RestController
public class ReportsController {

	@Autowired
	ReportService reportService;
	@RequestMapping(value = "/getEventQuestionNumbers", method = RequestMethod.GET,headers = "Accept=application/json",produces = "application/json")
	public ResponseEntity<Object> getEventquesionNumbers(HttpServletRequest request){
		HashMap<String,Integer> data = new HashMap<String,Integer>();
		
		List<EventDetails> questions = reportService.getApprovedVsRejectedQuestion();
		if(questions==null) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		Iterator<EventDetails> itr;
		itr = questions.iterator();
		while(itr.hasNext()) {
			EventDetails evt = itr.next();
			data.put(evt.getEventName(), evt.getTotalNoofApprovedQuestions());
		}
		
		
		return new ResponseEntity<>(data, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/getApprovedVsRejectedQuestions", method = RequestMethod.GET,headers = "Accept=application/json",produces = "application/json")
	public ResponseEntity<Object> getApprovedVsRejectedQuestion(HttpServletRequest request){
		List<EventDetails> questions = reportService.getApprovedVsRejectedQuestion();
		if(questions == null) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(questions, HttpStatus.OK);
		
	}
	
	
	
}
