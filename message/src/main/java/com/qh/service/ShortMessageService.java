package com.qh.service;

import com.qh.application.ShortMessageSendCommand;
import com.qh.domain.Mobile;
import com.qh.domain.MobileClient;
import com.qh.domain.RedisClient;
import com.qh.domain.ShortMessage;
import com.qh.infra.redis.SmsCodeKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Duration;

/**
 * @author Jinx
 */
@Service
@RequiredArgsConstructor
public class ShortMessageService {

    private final MobileClient mobileClient;
    private final RedisClient redisClient;

    public void send(ShortMessageSendCommand command) {
        // FIXME 限流

        Mobile mobile = new Mobile(command.getMobile());
        SmsCodeKey key = new SmsCodeKey(mobile.getNumber());

        String code = redisClient.get(key, String.class);
        if (StringUtils.hasText(code)) {
            throw new UnsupportedOperationException("don't send repeatedly");
        }

        // FIXME 应该放在domain？
        ShortMessage message = new ShortMessage();
        redisClient.set(key, message, message.getExpireDuration());

        mobileClient.sendShortMessage(mobile, message);
    }
}
