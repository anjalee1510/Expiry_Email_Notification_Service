package dev.anjalee.expiry_email_notification_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@EnableKafka
public class ExpiryEmailNotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpiryEmailNotificationServiceApplication.class, args);
	}

}
