package com.kafka.kafkaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages="com.kafka.kafkaconsumer")

public class ReactiveKafkaWebsocketApplication {
	 public static void main(String[] args) {
	        SpringApplication.run(ReactiveKafkaWebsocketApplication.class, args);
	        System.out.println("started the consumer");
	    }
}
