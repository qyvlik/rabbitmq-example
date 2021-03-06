package io.github.qyvlik.rabbitmqfanoutexample.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ListenerB {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @RabbitListener(queues = "${rabbitmq.fanout.queue.b}")
    public void handleQueueB(int index) {
        logger.info("handleQueueB:{} {}", index, Thread.currentThread().getId());
    }

}
