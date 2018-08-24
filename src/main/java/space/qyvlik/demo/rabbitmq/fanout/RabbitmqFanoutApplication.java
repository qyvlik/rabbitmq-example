package space.qyvlik.demo.rabbitmq.fanout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import space.qyvlik.demo.rabbitmq.fanout.mq.Sender;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RabbitmqFanoutApplication {

    @Autowired
    private Sender sender;

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqFanoutApplication.class, args);
    }

    @PostConstruct
    public void send() {
        int count = 10;
        while (count-- > 0) {
            sender.send(count);
        }
    }
}
