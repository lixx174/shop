package com.qh.infra.redis;

/**
 * @author Jinx
 */
public class SmsCodeKey extends BasicRedisKey {
    public SmsCodeKey(String tenant) {
        super("shop:authorization:sms", tenant);
    }
}
