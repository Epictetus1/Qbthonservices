package com.qbthon.kafkaservices.models;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class McqSerializer implements Serializer<MultipleChoiceQuestion>{

	

	@Override
	public byte[] serialize(String topic, MultipleChoiceQuestion data) {
		// TODO Auto-generated method stub
		byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
        retVal = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception exception) {
        System.out.println("Error in serializing object"+ data);
        }
        return retVal;
	}

	



}
