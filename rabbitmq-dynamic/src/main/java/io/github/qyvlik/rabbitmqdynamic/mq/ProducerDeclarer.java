package io.github.qyvlik.rabbitmqdynamic.mq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;

public class ProducerDeclarer {
    private RabbitAdmin rabbitAdmin;

    public RabbitAdmin getRabbitAdmin() {
        return rabbitAdmin;
    }

    public void setRabbitAdmin(RabbitAdmin rabbitAdmin) {
        this.rabbitAdmin = rabbitAdmin;
    }

    public Queue declareQueue(String queueName, boolean durable) {
        return declareInternal(queueName, durable, this.getRabbitAdmin());
    }

    public FanoutExchange declareFanoutExchange(String name, boolean durable, boolean autoDelete) {
        return declareFanoutExchange(name, durable, autoDelete, this.getRabbitAdmin());
    }

    public Binding bindingFanoutExchange4Queue(FanoutExchange fanoutExchange,
                                               Queue queue) {
        return bindingFanoutExchange4QueueInternal(
                fanoutExchange,
                queue,
                this.getRabbitAdmin());
    }

    public Binding bindingTopicExchange4Queue(TopicExchange topicExchange,
                                              Queue queue,
                                              String routingKey) {
        return bindingTopicExchange4QueueInternal(
                topicExchange,
                queue,
                routingKey,
                this.getRabbitAdmin());
    }

    protected Queue declareInternal(String queueName, boolean durable, RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(queueName, durable);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

    protected Binding bindingFanoutExchange4QueueInternal(FanoutExchange fanoutExchange,
                                                          Queue queue,
                                                          RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queue).to(fanoutExchange);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    protected Binding bindingTopicExchange4QueueInternal(TopicExchange topicExchange,
                                                         Queue queue,
                                                         String routingKey,
                                                         RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(queue).to(topicExchange).with(routingKey);
        rabbitAdmin.declareBinding(binding);
        return binding;
    }

    protected FanoutExchange declareFanoutExchange(String name, boolean durable, boolean autoDelete, RabbitAdmin rabbitAdmin) {
        FanoutExchange fanoutExchange = new FanoutExchange(name, durable, autoDelete);
        rabbitAdmin.declareExchange(fanoutExchange);
        return fanoutExchange;
    }
}
