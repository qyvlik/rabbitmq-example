package io.github.qyvlik.rabbitmqdynamic.config;

import io.github.qyvlik.rabbitmqdynamic.mq.ListenerInstaller;
import io.github.qyvlik.rabbitmqdynamic.mq.ProducerDeclarer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqDynamicConfig {

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean("producerDeclarer")
    public ProducerDeclarer producerDeclarer(RabbitAdmin rabbitAdmin) {
        ProducerDeclarer producerDeclarer = new ProducerDeclarer();
        producerDeclarer.setRabbitAdmin(rabbitAdmin);
        return producerDeclarer;
    }

    @Bean("listenerInstaller")
    public ListenerInstaller listenerInstaller(
            ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        ListenerInstaller listenerInstaller = new ListenerInstaller();

        listenerInstaller.setConnectionFactory(connectionFactory);
        listenerInstaller.setMessageConverter(jackson2JsonMessageConverter);

        return listenerInstaller;
    }
}
