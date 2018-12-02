package io.github.qyvlik.rabbitmqdynamic.mq;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

public class DynamicMessageListenerContainer extends SimpleMessageListenerContainer {
    public void startConsumers() throws Exception {
        super.doStart();
    }
}
