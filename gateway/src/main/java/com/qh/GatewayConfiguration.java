package com.qh;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Jinx
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "gateway.security")
public class GatewayConfiguration {


    private Whitelist whitelist = new Whitelist();
    private Map<String, RateLimiter> rateLimiter = new HashMap<>();
    private GrayRelease grayRelease = new GrayRelease();

    /**
     * 灰度发布
     */
    @Getter
    @Setter
    public static class GrayRelease {
        private boolean enable;
        private Collection<String> ips = new HashSet<>();
    }

    /**
     * 限流
     */
    @Getter
    @Setter
    public static class RateLimiter {
        private Integer replenishRate, burstCapacity;

        public RateLimiter(String text) {
            String[] split = StringUtils.commaDelimitedListToStringArray(text);
            Assert.isTrue(split.length == 2, "illegal rateLimiter config: %s".formatted(text));

            try {
                this.replenishRate = Integer.parseInt(split[0]);
                this.burstCapacity = Integer.parseInt(split[1]);
            }catch (Exception e){
                throw new IllegalArgumentException("illegal rateLimiter config: %s".formatted(text));
            }
        }
    }

    /**
     * 白名单
     */
    @Getter
    @Setter
    public static class Whitelist {
        /**
         * TODO path support http method
         */
        private Set<String> ips = new HashSet<>(), paths = new HashSet<>();
    }
}
