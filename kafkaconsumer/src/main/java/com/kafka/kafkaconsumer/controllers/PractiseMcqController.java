package com.kafka.kafkaconsumer.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.kafka.kafkaconsumer.service.ConsumerTester;

@Controller
public class PractiseMcqController {
@Autowired 
ConsumerTester consumerTester;

	 @MessageMapping("/getQuestions")
	  @SendTo("/topic/practisemcq")
	  public ArrayList<String> questions() throws Exception {
	    Thread.sleep(1000); // simulated delay
	    System.out.println("got the websocket request");
	    return consumerTester.getQuestions();
	  }
}
