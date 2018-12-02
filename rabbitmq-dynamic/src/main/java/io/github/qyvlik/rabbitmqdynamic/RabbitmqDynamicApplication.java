package io.github.qyvlik.rabbitmqdynamic;

import io.github.qyvlik.rabbitmqdynamic.handle.ConsumerHandle;
import io.github.qyvlik.rabbitmqdynamic.handle.MyMessage;
import io.github.qyvlik.rabbitmqdynamic.mq.ListenerInstaller;
import io.github.qyvlik.rabbitmqdynamic.mq.ProducerDeclarer;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RabbitmqDynamicApplication {

    @Autowired
    @Qualifier("producerDeclarer")
    private ProducerDeclarer producerDeclarer;

    @Autowired
    @Qualifier("listenerInstaller")
    private ListenerInstaller listenerInstaller;

    @Autowired
    @Qualifier("consumerHandle")
    private ConsumerHandle consumerHandle;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqDynamicApplication.class, args);
    }

    @PostConstruct
    public void testDynamic() {
        String queueName = "dynamic.queue";
        String routingKey = "dynamic.fanout";

        Queue queue = producerDeclarer.declareQueue(queueName, false);
        FanoutExchange fanoutExchange = producerDeclarer.declareFanoutExchange(routingKey, false, true);
        producerDeclarer.bindingFanoutExchange4Queue(fanoutExchange, queue);

        listenerInstaller.install("",
                queueName,
                1,
                AcknowledgeMode.AUTO,
                consumerHandle);

        rabbitTemplate.convertAndSend(queueName, new MyMessage("here is form dynamic producer"));

    }
}
