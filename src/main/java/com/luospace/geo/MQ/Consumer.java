package com.luospace.geo.MQ;
import com.luospace.geo.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    @RabbitListener(queues = RabbitMQConfig.IMMEDIATE_QUEUE)
    public void processMessage(String content) {
        logger.info("当前时间：{},收到请求，msg:{},delayTime:{}", new Date(), content, 20000);
        System.out.println("接受的消息： "+content);
    }
}
