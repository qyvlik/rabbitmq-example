package io.github.qyvlik.rabbitmqfanoutexample.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ListenerA {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitListener(queues = "${rabbitmq.fanout.queue.a}")
    public void handleQueueA(int index) {
        logger.info("handleQueueA:{} {}", index, Thread.currentThread().getId());
    }
}
