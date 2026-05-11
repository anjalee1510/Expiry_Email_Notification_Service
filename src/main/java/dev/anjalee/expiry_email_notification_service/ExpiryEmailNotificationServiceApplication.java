package dev.anjalee.expiry_email_notofication_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@KafkaListener(topics = "membership-expiry-email-topic", groupId = "expiry-email-notification-service-group")
public class ExpiryEmailNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpiryEmailNotificationServiceApplication.class, args);
	}

}
