package com.kafka.kafkaconsumer.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import org.springframework.stereotype.Service;

import com.kafka.kafkaconsumer.configs.AppConfigs;
import com.kafka.kafkaconsumer.models.McqDeSerializer;
import com.kafka.kafkaconsumer.models.MultipleChoiceQuestion;

import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

@Service
public class KafkaService {
	 private Flux<ReceiverRecord<String, String>> testTopicStream;


	 KafkaService() throws IOException {

	        Properties kafkaProperties = new Properties();
	        kafkaProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
	        kafkaProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);

	    
	        kafkaProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "sample-group");
	        kafkaProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        kafkaProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        kafkaProperties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

	        ReceiverOptions<String, String> receiverOptions = ReceiverOptions.create(kafkaProperties);

	        testTopicStream = createTopicCache(receiverOptions, "userpractise");
	    }


	    public Flux<ReceiverRecord<String, String>> getTestTopicFlux() {

	        return testTopicStream;
	    }

	    private <T,G> Flux<ReceiverRecord<T,G>> createTopicCache(ReceiverOptions<T,G> receiverOptions, String topicName){
	        ReceiverOptions<T,G> options = receiverOptions.subscription(Collections.singleton(topicName));

	        return KafkaReceiver.create(options).receive().cache();
	    }
}
