package io.github.qyvlik.rabbitmqfanoutexample.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.fanout.exchange}")
    private String fanoutExchangeName;

    public void send(int index) {
        rabbitTemplate.convertAndSend(fanoutExchangeName, "", index);
    }
}
