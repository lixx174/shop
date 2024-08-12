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

/**
 * @author Jinx
 */
@Service
@RequiredArgsConstructor
public class ShortMessageService {

    private final MobileClient mobileClient;
    private final RedisClient redisClient;

    public void send(ShortMessageSendCommand command) {
        Mobile mobile = new Mobile(command.getMobile());

        String code = redisClient.get(new SmsCodeKey(mobile.getNumber()), String.class);
        if (StringUtils.hasText(code)) {
            throw new UnsupportedOperationException("don't send repeatedly");
        }

        mobileClient.sendShortMessage(mobile, new ShortMessage());
    }
}
