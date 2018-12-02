package io.github.qyvlik.rabbitmqdynamic.handle;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

public class ConsumerHandle implements ChannelAwareMessageListener {
    private Jackson2JsonMessageConverter jackson2JsonMessageConverter;

    private Logger logger = LoggerFactory.getLogger(getClass());


    public Jackson2JsonMessageConverter getJackson2JsonMessageConverter() {
        return jackson2JsonMessageConverter;
    }

    public void setJackson2JsonMessageConverter(Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
        this.jackson2JsonMessageConverter = jackson2JsonMessageConverter;
    }

    public void handleMessage(MyMessage myMessage, Message message, Channel channel) {
        logger.info("here is dynamic consumer handle:{}", myMessage);
    }

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        MyMessage myMessage = (MyMessage) jackson2JsonMessageConverter.fromMessage(message);

        handleMessage(myMessage, message, channel);
    }
}
