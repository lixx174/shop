package com.qh.infra.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

/**
 * @author Jinx
 */
@Component
@RequiredArgsConstructor
public class RedisOAuth2AuthorizationService implements OAuth2AuthorizationService {

    private final RedissonClient redisson;
    private final ObjectMapper om;


    @Override
    public void save(OAuth2Authorization authorization) {
        OAuth2Authorization.Token<OAuth2AccessToken> accessToken = authorization.getAccessToken();
        OAuth2Authorization.Token<OAuth2RefreshToken> refreshToken = authorization.getRefreshToken();

        // FIXME 发一次请求
        redisson.getBucket(redisKey(authorization.getId()))
                .set(authorization, Duration.between(Instant.now(), accessToken.getToken().getExpiresAt()));

        redisson.getBucket(redisKey(accessToken.getToken().getTokenValue(), OAuth2TokenType.ACCESS_TOKEN))
                .set(authorization, Duration.between(Instant.now(), accessToken.getToken().getExpiresAt()));
        if (refreshToken != null) {
            redisson.getBucket(redisKey(refreshToken.getToken().getTokenValue(), OAuth2TokenType.REFRESH_TOKEN))
                    .set(authorization, Duration.between(Instant.now(), refreshToken.getToken().getExpiresAt()));
        }
    }

    @Override
    public void remove(OAuth2Authorization authorization) {
        redisson.getBucket(redisKey(authorization.getId())).delete();
    }

    @Override
    public OAuth2Authorization findById(String id) {
        return deserialize(redisson.getBucket(redisKey(id)).get());
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        return deserialize(redisson.getBucket(redisKey(token, tokenType)).get());
    }


    private String redisKey(String id) {
        return "shop:authorization:token_id:" + id;
    }

    private String redisKey(String token, OAuth2TokenType tokenType) {
        return "shop:authorization:" + tokenType.getValue() + ":" + token;
    }

    private String serialize(Object obj) {
        try {
            return obj == null ? null : om.writeValueAsString(obj);
        } catch (Exception e) {
            return null;
        }
    }

    private OAuth2Authorization deserialize(Object obj) {
        try {
            return obj == null ? null : om.readValue(obj.toString(), OAuth2Authorization.class);
        } catch (Exception e) {
            return null;
        }
    }
}
