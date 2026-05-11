package dev.anjalee.expiry_email_notification_service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.anjalee.expiry_email_notification_service.dtos.EmailEventDTO;
import dev.anjalee.expiry_email_notification_service.service.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EmailEventListener {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmailNotificationService emailNotificationService;
    @KafkaListener(topics = "membership-expiry-email-topic", groupId = "expiry-email-notification-service-group")
    public void listen(String message) {

        System.out.println("Received message: " + message);
        try {
            EmailEventDTO emailEvent = objectMapper.readValue(message, EmailEventDTO.class);
            emailNotificationService.sendEmailWithAttachment(emailEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert kafka message to EmailEventDTO", e);
        }

    }
}

