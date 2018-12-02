package io.github.qyvlik.rabbitmqdynamic.handle;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;

public class ConsumerHandle {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // todo, not support fill the Message and Channel args
    public void handleMessage(MyMessage myMessage, Message message, Channel channel) {
        logger.info("here is dynamic consumer handle:{}", myMessage);
    }

    public void handleMessage(MyMessage myMessage) {
        logger.info("here is dynamic consumer handle:{}", myMessage);
    }
}
