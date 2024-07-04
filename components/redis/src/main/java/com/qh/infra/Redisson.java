package com.qh.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qh.domain.RedisClient;
import com.qh.domain.RedisKey;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.util.Assert;

import java.time.Duration;

/**
 * @author Jinx
 */
@RequiredArgsConstructor
public class Redisson implements RedisClient {

    private final RedissonClient rc;
    private final ObjectMapper om;

    @Override
    public <T> T get(RedisKey key, Class<T> type) {
        Object obj = rc.getBucket(key.value()).get();

        if (obj != null) {
            try {
                return om.readValue(obj.toString(), type);
            } catch (JsonProcessingException e) {
                throw new ClassCastException("class cast exception, source:%s except:%s".formatted(obj, type));
            }
        }

        return null;
    }

    @Override
    public void set(RedisKey key, Object value) {
        set(key, value, Duration.ZERO);
    }

    @Override
    public void set(RedisKey key, Object value, Duration expiration) {
        Assert.isTrue(key != null && key.hasText(), "key can't be empty");
        Assert.notNull(expiration, "expiration can't be null");

        if (expiration == Duration.ZERO) {
            rc.getBucket(key.value()).set(value);
        } else {
            rc.getBucket(key.value()).set(value, expiration);
        }
    }

    @Override
    public void delete(RedisKey key) {
        rc.getBucket(key.value()).delete();
    }
}
