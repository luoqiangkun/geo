package com.luospace.geo.MQ;

import com.luospace.geo.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Slf4j
public class Producer {

    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Resource
    private RabbitTemplate rabbitTemplate;


    public void send(String msg) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.DEAD_LETTER_EXCHANGE, RabbitMQConfig.DELAY_ROUTING_KEY, msg, message -> {
            message.getMessageProperties().setExpiration("20000");
            return message;
        });
    }
}
