package io.github.qyvlik.rabbitmqdynamic.mq;


import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.MessageConverter;

public class ListenerInstaller {

    private ConnectionFactory connectionFactory;
    private MessageConverter messageConverter;

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public MessageConverter getMessageConverter() {
        return messageConverter;
    }

    public void setMessageConverter(MessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    public void install(String routingKey,
                        String queueName,
                        int onOfConsumer,
                        AcknowledgeMode acknowledgeMode,
                        Object handle) {
        try {
            installInternal(
                    this.getConnectionFactory(),
                    this.getMessageConverter(),
                    routingKey,
                    queueName,
                    onOfConsumer,
                    acknowledgeMode,
                    handle
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    protected void installInternal(ConnectionFactory connectionFactory,
                                   MessageConverter messageConverter,
                                   String routingKey,
                                   String queueName,
                                   int onOfConsumer,
                                   AcknowledgeMode acknowledgeMode,
                                   Object handle) throws Exception {
        DynamicMessageListenerContainer container
                = new DynamicMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setConcurrentConsumers(onOfConsumer);
        container.setAcknowledgeMode(acknowledgeMode);
        container.setMessageListener(new MessageListenerAdapter(handle, messageConverter));
        container.startConsumers();
    }

}
