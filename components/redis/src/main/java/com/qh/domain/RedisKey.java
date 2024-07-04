package com.qh.domain;

/**
 * @author Jinx
 */
public interface RedisKey {

    /**
     * 获取 key 值
     *
     * @return key 值
     */
    String value();

    /**
     * key是否有内容
     *
     * @return true：有内容
     */
    default boolean hasText() {
        String value = value();
        return value != null && !value.isBlank();
    }
}
