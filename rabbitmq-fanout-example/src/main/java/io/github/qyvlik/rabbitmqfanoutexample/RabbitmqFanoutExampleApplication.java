package io.github.qyvlik.rabbitmqfanoutexample;

import io.github.qyvlik.rabbitmqfanoutexample.mq.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RabbitmqFanoutExampleApplication {

	@Autowired
	private Sender sender;

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqFanoutExampleApplication.class, args);
	}

//	@PostConstruct
	public void send() {
		int count = 10;
		while (count-- > 0) {
			sender.send(count);
		}
	}
}
