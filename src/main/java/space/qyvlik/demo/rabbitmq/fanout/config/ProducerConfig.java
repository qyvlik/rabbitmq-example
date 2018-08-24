package space.qyvlik.demo.rabbitmq.fanout.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@Configuration
public class ProducerConfig {

    @Value("${rabbitmq.fanout.exchange}")
    private String fanoutExchangeName;

    @Value("${rabbitmq.fanout.queue.a}")
    private String queueA;

    @Value("${rabbitmq.fanout.queue.b}")
    private String queueB;

    private static Binding bindingFanoutExchange4Queue(Queue queue, FanoutExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queue).to(exchange);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public RabbitMessagingTemplate rabbitMessagingTemplate(RabbitTemplate rabbitTemplate) {
        RabbitMessagingTemplate rabbitMessagingTemplate = new RabbitMessagingTemplate();
        rabbitMessagingTemplate.setMessageConverter(jackson2Converter());
        rabbitMessagingTemplate.setRabbitTemplate(rabbitTemplate);
        return rabbitMessagingTemplate;
    }

    @Bean
    public MappingJackson2MessageConverter jackson2Converter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        return converter;
    }

    @Bean("fanoutExchange")
    public FanoutExchange fanoutExchange(RabbitAdmin rabbitAdmin) {
        FanoutExchange fanoutExchange = new FanoutExchange(fanoutExchangeName, true, false);
        rabbitAdmin.declareExchange(fanoutExchange);
        return fanoutExchange;
    }

    @Bean("queueA")
    public Queue queueA(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueA, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean("queueB")
    public Queue queueB(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueB, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    @Bean
    public Binding bindingFanoutExchange4QueueA(@Qualifier("queueA") Queue queueA,
                                                @Qualifier("fanoutExchange") FanoutExchange exchange,
                                                RabbitAdmin rabbitAdmin) {
        return bindingFanoutExchange4Queue(queueA, exchange, rabbitAdmin);
    }

    @Bean
    public Binding bindingFanoutExchange4QueueB(@Qualifier("queueB") Queue queueB,
                                                @Qualifier("fanoutExchange") FanoutExchange exchange,
                                                RabbitAdmin rabbitAdmin) {
        return bindingFanoutExchange4Queue(queueB, exchange, rabbitAdmin);
    }
}