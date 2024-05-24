package com.qh;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.UUID;

/**
 * @author Jinx
 */
@SpringBootTest
public class RegisteredClientRepositoryTest {

    @Autowired
    RegisteredClientRepository registeredClientRepo;

    @Test
    @DisplayName("注册客户端")
    public void register() {
        registeredClientRepo.save(
                RegisteredClient.withId(UUID.randomUUID().toString())
                        .clientId("shop-app")
                        .clientSecret("{noop}shop-secret")
                        .clientName("shop-app")
                        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                        .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                        .redirectUri("http://127.0.0.1:8080/callback")
                        .postLogoutRedirectUri("http://127.0.0.1:8080")
                        .scope(OidcScopes.OPENID)
                        .clientSettings(
                                ClientSettings.builder()
                                        .requireAuthorizationConsent(true)
                                        .build()
                        )
                        .tokenSettings(
                                TokenSettings.builder()
                                        .accessTokenFormat(OAuth2TokenFormat.REFERENCE)
                                        .accessTokenTimeToLive(Duration.ofDays(1))
                                        .refreshTokenTimeToLive(Duration.ofDays(7))
                                        .build()
                        )
                        .build()
        );
    }
}
