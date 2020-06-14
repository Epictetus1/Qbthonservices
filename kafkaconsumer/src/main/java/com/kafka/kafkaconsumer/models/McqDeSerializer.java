package com.kafka.kafkaconsumer.models;



import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.common.serialization.Deserializer;


public class McqDeSerializer  implements  Deserializer<MultipleChoiceQuestion> {

	@Override
	public MultipleChoiceQuestion deserialize(String topic, byte[] data) {
		// TODO Auto-generated method stub
		
		ObjectMapper objectMapper = new ObjectMapper();
		MultipleChoiceQuestion mcq = null;
		   try {
		     mcq = objectMapper.readValue(data, MultipleChoiceQuestion.class);
		   } catch (Exception e) {
		     e.printStackTrace();
		   }
		   return mcq;
		
	}

	
}
