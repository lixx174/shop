package com.qh.infra.redis;

/**
 * @author Jinx
 */
public class UserDetailKey extends BasicRedisKey {
    public UserDetailKey(String tenant) {
        super("shop:authorization:ud", tenant);
    }
}
