package com.kafka.kafkaconsumer.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    public Message(String string, String string2) {
		// TODO Auto-generated constructor stub
    	this.type = string2;
    	this.message = string;
	}
	private String type;
    private String message;
}