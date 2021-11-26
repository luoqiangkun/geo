package com.luospace.geo.controller;

import com.luospace.geo.MQ.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class MQController {

    private final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Resource
    Producer producer;

    @RequestMapping("/send/{message}")
    public void send(@PathVariable("message") String message){
        logger.info("当前时间：{},发送请求，msg:{},delayTime:{}", new Date(), message, 20000);
        producer.send(message);
    }
}
