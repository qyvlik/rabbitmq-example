package io.github.qyvlik.rabbitmqdynamic.config;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@Configuration
@EnableRabbit
public class RabbitmqGeneralConfig {

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public MappingJackson2MessageConverter mappingJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public RabbitMessagingTemplate rabbitMessagingTemplate(
            RabbitTemplate rabbitTemplate,
            MappingJackson2MessageConverter mappingJackson2MessageConverter) {
        RabbitMessagingTemplate rabbitMessagingTemplate = new RabbitMessagingTemplate();
        rabbitMessagingTemplate.setMessageConverter(mappingJackson2MessageConverter);
        rabbitMessagingTemplate.setRabbitTemplate(rabbitTemplate);
        return rabbitMessagingTemplate;
    }


}
