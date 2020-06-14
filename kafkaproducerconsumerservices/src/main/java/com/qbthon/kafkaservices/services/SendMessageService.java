package com.qbthon.kafkaservices.services;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data

@Service
public class SendMessageService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private static final String WS_MESSAGE_TRANSFER_DESTINATION = "/topic/greetings";

    SendMessageService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendMessages() {
        simpMessagingTemplate.convertAndSend(WS_MESSAGE_TRANSFER_DESTINATION,
            "Hallo " + " at " + new Date().toString());
    }
}