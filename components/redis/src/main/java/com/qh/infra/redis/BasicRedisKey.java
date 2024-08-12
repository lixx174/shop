package com.qh.infra.redis;

import com.qh.domain.RedisKey;
import org.springframework.util.StringUtils;

/**
 * 常规key，会根据一个唯一键来区分
 *
 * @author Jinx
 */
public abstract class BasicRedisKey implements RedisKey {

    /**
     * 前缀
     */
    private final String prefix;
    /**
     * 唯一键  类似该prefix下的租户
     */
    private final String tenant;

    protected BasicRedisKey(String prefix, String tenant) {
        // FIXME prefix规范  自动填充 OR 强制
        this.prefix = StringUtils.hasText(prefix) ? prefix : "shop:base";
        this.tenant = StringUtils.hasText(tenant) ? tenant : null;
    }

    @Override
    public String value() {
        return prefix + ":" + tenant;
    }
}
