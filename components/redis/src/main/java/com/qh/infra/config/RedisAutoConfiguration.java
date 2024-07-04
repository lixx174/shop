package com.qh.infra.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.qh.domain.RedisClient;
import com.qh.infra.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jinx
 */
@Configuration
@ConditionalOnMissingBean(RedisClient.class)
public class RedisAutoConfiguration {


    @Bean
    public RedisClient redisClient(RedissonClient rc, ObjectMapper om) {
        return new Redisson(rc, om);
    }
}
