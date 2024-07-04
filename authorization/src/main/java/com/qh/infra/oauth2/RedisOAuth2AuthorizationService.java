package com.qh.infra.oauth2;

import com.qh.domain.RedisClient;
import lombok.RequiredArgsConstructor;
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

    private final RedisClient redisClient;


    @Override
    public void save(OAuth2Authorization authorization) {
        OAuth2Authorization.Token<OAuth2AccessToken> accessToken = authorization.getAccessToken();
        OAuth2Authorization.Token<OAuth2RefreshToken> refreshToken = authorization.getRefreshToken();

        // FIXME 发一次请求
        redisClient.set(
                () -> redisKey(authorization.getId()),
                authorization,
                Duration.between(Instant.now(), accessToken.getToken().getExpiresAt())
        );

        redisClient.set(
                () -> redisKey(accessToken.getToken().getTokenValue(), OAuth2TokenType.ACCESS_TOKEN),
                authorization,
                Duration.between(Instant.now(), accessToken.getToken().getExpiresAt())
        );
        if (refreshToken != null) {
            redisClient.set(
                    () -> redisKey(refreshToken.getToken().getTokenValue(), OAuth2TokenType.REFRESH_TOKEN),
                    authorization,
                    Duration.between(Instant.now(), refreshToken.getToken().getExpiresAt())
            );
        }
    }

    @Override
    public void remove(OAuth2Authorization authorization) {
        redisClient.delete(() -> redisKey(authorization.getId()));
    }

    @Override
    public OAuth2Authorization findById(String id) {
        return redisClient.get(() -> redisKey(id), OAuth2Authorization.class);
    }

    @Override
    public OAuth2Authorization findByToken(String token, OAuth2TokenType tokenType) {
        return redisClient.get(() -> redisKey(token, tokenType), OAuth2Authorization.class);
    }


    private String redisKey(String id) {
        return "shop:authorization:token_id:" + id;
    }

    private String redisKey(String token, OAuth2TokenType tokenType) {
        return "shop:authorization:" + tokenType.getValue() + ":" + token;
    }
}
