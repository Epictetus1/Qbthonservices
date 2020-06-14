package com.qbthon.qbthonservices.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.qbthon.qbthonservices.models.MultipleChoiceQuestion;


@CrossOrigin
@RestController
public class PractisemcqController {
	@Value("${kafkaservices.url}")
	private String kafkaurl;

	@RequestMapping(value = "/practisemcq", method = RequestMethod.POST,headers = "Accept=application/json",produces = "application/json")
	public ResponseEntity<Object> submitPractiseMcq(HttpServletRequest request,@RequestBody MultipleChoiceQuestion multipleChoiceQuestion) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	

		HttpEntity<MultipleChoiceQuestion> entity = new HttpEntity<>(multipleChoiceQuestion, headers);
		kafkaurl = kafkaurl+"/submittopic/practisemcq/";
		ResponseEntity<String> result = restTemplate.exchange(kafkaurl, HttpMethod.POST, entity, String.class);
		
		if(result.getStatusCode().isError()) {
			return new ResponseEntity<>("error in submitting questions"+result.getBody(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("practise question submitted", HttpStatus.OK);
		
	}
}
