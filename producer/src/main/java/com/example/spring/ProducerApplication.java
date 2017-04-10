package com.example.spring;

import oracle.jvm.hotspot.jfr.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(Events.class)
@SpringBootApplication
public class ProducerApplication {

	Logger log = LoggerFactory.getLogger(ProducerApplication.class);

	private final MessageChannel messageChannel;

	public ProducerApplication(Events producerChannel) {
		this.messageChannel = producerChannel.workload();
	}

	@PostMapping("/greet/{name}")
	public void publish (@PathVariable String name) {
		String greeting = "Hello, " + name;
		Message<String> msg = MessageBuilder.withPayload(greeting).build();
		messageChannel.send(msg);
		log.info("Message sent to messageChannel channel : " + greeting);
	}

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}
}

interface Events {
	@Output MessageChannel workload();
}