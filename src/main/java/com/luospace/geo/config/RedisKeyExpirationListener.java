package com.luospace.geo.config;

import com.luospace.geo.common.Constant;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();
        System.out.println(expiredKey);
        if (expiredKey.startsWith(Constant.REDIS_ORDER_UNPAID_PREFIX)) {
            // 获取订单orderNO
            String orderNo = expiredKey.substring(expiredKey.lastIndexOf(":")+1);
            // 将待支付的订单改为已取消(超时未支付)
            System.out.println("订单超时未支付自动取消，订单号为：" + orderNo);
        }
    }
}
