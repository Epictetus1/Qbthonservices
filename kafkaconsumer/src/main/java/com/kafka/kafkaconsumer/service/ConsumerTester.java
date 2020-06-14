package com.kafka.kafkaconsumer.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import com.kafka.kafkaconsumer.configs.AppConfigs;
import com.kafka.kafkaconsumer.models.JsonDeserializer;
import com.kafka.kafkaconsumer.models.McqDeSerializer;
import com.kafka.kafkaconsumer.models.MultipleChoiceQuestion;

@Service
public class ConsumerTester {
	@Autowired
	static SendMessageService sendMessageService;
	public ArrayList<String> getQuestions(){
		ArrayList<String> questions = new ArrayList<String>();
		Properties kafkaProperties = new Properties();
	     kafkaProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
	     kafkaProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);

	 
	     kafkaProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "sample-group");
	     kafkaProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	     kafkaProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	     kafkaProperties.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, MultipleChoiceQuestion.class);
	     kafkaProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
	     
	     KafkaConsumer<String, String> consumer = new KafkaConsumer<>(kafkaProperties);
	     consumer.subscribe(Arrays.asList(AppConfigs.sourceTopicNames));
	     
	    
	    
	  	 
	   for(int i=0;i<5;i++) {
	     ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
	    	 System.out.println("record length is "+records.count());
	     for (ConsumerRecord<String, String> record : records) {
	    	 questions.add(record.value());
	    }
	    
	    }
	   return questions;
	}
	
	public static void main(String args[]) {
		
	 Properties kafkaProperties = new Properties();
     kafkaProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
     kafkaProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);

 
     kafkaProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "sample-group");
     kafkaProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
     kafkaProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
     kafkaProperties.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, MultipleChoiceQuestion.class);
     kafkaProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
     
     KafkaConsumer<String, String> consumer = new KafkaConsumer<>(kafkaProperties);
     consumer.subscribe(Arrays.asList(AppConfigs.sourceTopicNames));
     
    
    
  	 
   for(int i=0;i<5;i++) {
     ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
    	 System.out.println("record length is "+records.count());
     for (ConsumerRecord<String, String> record : records) {
    	 System.out.println(record.value());
    	sendMessageService.sendMessages(record.value());
    }
    
    }
	}
	
     
     


}
