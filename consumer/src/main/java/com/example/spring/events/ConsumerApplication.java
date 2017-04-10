package com.example.spring.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

@EnableBinding(Sink.class)
@SpringBootApplication
public class ConsumerApplication {

	private static Logger log = LoggerFactory.getLogger(ConsumerApplication.class);

	@StreamListener(Sink.INPUT)
	public void receiveMessage(String message) {
		log.info("Message received in consumer: " + message);
	}

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}
}