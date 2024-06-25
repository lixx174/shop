package com.qh.infra.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Jinx
 */
@Configuration
public class RedisConfig {

    /**
     * redis 操作模板
     *
     * @deprecated 用redisson  {@link RedissonAutoConfiguration#redisson()}
     */
//    @Bean
    @Deprecated
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory, ObjectMapper om) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(om, Object.class));

        return template;
    }
}
