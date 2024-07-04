package com.qh.domain;

import java.time.Duration;

/**
 * redis客户端接口，提供一系列和redis交互api
 *
 * @author Jinx
 */
public interface RedisClient {

    /**
     * 获取 string
     *
     * @param key  redis key
     * @param type class类型
     * @param <T>  期望返回的类型
     * @return redis value
     * @throws ClassCastException 无法转换为指定类型时会抛出该异常
     */
    <T> T get(RedisKey key, Class<T> type) throws ClassCastException;

    /**
     * 设置 string
     * <p>
     * 不过期
     *
     * @param key   redis key
     * @param value redis value
     */
    void set(RedisKey key, Object value);

    /**
     * 设置 string
     *
     * @param key        redis key
     * @param value      redis value
     * @param expiration 过期时间
     */
    void set(RedisKey key, Object value, Duration expiration);

    /**
     * 删除string
     *
     * @param key redis key
     */
    void delete(RedisKey key);
}
