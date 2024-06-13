package com.qh.infra.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AccessTokenAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.context.AuthorizationServerContextHolder;
import org.springframework.security.oauth2.server.authorization.token.DefaultOAuth2TokenContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
import org.springframework.util.Assert;

/**
 * @author Jinx
 */
@RequiredArgsConstructor
public class Oauth2SmsAuthenticationProvider implements AuthenticationProvider {

    private final OAuth2TokenGenerator<OAuth2Token> tokenGenerator;
    private final OAuth2AuthorizationService authorizationService;
    private static final String ACCESS_TOKEN_REQUEST_ERROR_URI = "www.baidu.com";


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Oauth2SmsAuthenticationToken smsAuthentication = (Oauth2SmsAuthenticationToken) authentication;

        OAuth2ClientAuthenticationToken authenticatedClient = getAuthenticatedClient(authentication);
        RegisteredClient registeredClient = authenticatedClient.getRegisteredClient();

        if (registeredClient == null) {
            throw new OAuth2AuthenticationException(
                    new OAuth2Error(
                            OAuth2ErrorCodes.INVALID_CLIENT,
                            "registered client not find",
                            ACCESS_TOKEN_REQUEST_ERROR_URI
                    )
            );
        }

        // TODO redis存短信


        // tokenContextBuilder
        DefaultOAuth2TokenContext.Builder tokenContextBuilder = DefaultOAuth2TokenContext.builder()
                .registeredClient(registeredClient)
                .principal(authenticatedClient)
                .authorizationServerContext(AuthorizationServerContextHolder.getContext())
                .tokenType(OAuth2TokenType.ACCESS_TOKEN)
                .authorizationGrantType(Oauth2SmsAuthenticationConverter.GRANT_TYPE)
                .authorizationGrant(smsAuthentication);

        OAuth2Token generatedAccessToken =
                tokenGenerator.generate(tokenContextBuilder.tokenType(OAuth2TokenType.ACCESS_TOKEN).build());
        Assert.isTrue(
                generatedAccessToken instanceof OAuth2AccessToken,
                "token generator failed to generate the access token"
        );
        // 强制使用 OAuth2AccessToken  默认会使用 OAuth2AccessTokenClaims
        OAuth2AccessToken accessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER,
                generatedAccessToken.getTokenValue(), generatedAccessToken.getIssuedAt(),
                generatedAccessToken.getExpiresAt(), ((OAuth2AccessToken) generatedAccessToken).getScopes());

        OAuth2Token refreshToken =
                tokenGenerator.generate(tokenContextBuilder.tokenType(OAuth2TokenType.REFRESH_TOKEN).build());
        Assert.isTrue(
                refreshToken instanceof OAuth2RefreshToken,
                "token generator failed to generate the refresh token"
        );

        // 保存token
        authorizationService.save(
                OAuth2Authorization.withRegisteredClient(registeredClient)
                        .principalName(authenticatedClient.getName())
                        .authorizationGrantType(Oauth2SmsAuthenticationConverter.GRANT_TYPE)
                        .accessToken(accessToken)
                        .refreshToken((OAuth2RefreshToken) refreshToken)
                        .build()
        );

        return new OAuth2AccessTokenAuthenticationToken(
                registeredClient,
                authenticatedClient,
                accessToken,
                (OAuth2RefreshToken) refreshToken
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return Oauth2SmsAuthenticationToken.class.isAssignableFrom(authentication);
    }


    private OAuth2ClientAuthenticationToken getAuthenticatedClient(Authentication authentication) {
        if (authentication.getPrincipal() instanceof OAuth2ClientAuthenticationToken clientPrincipal &&
                clientPrincipal.isAuthenticated()) {
            return clientPrincipal;
        }

        throw new OAuth2AuthenticationException(
                new OAuth2Error(
                        OAuth2ErrorCodes.INVALID_CLIENT,
                        "client not authenticate",
                        ACCESS_TOKEN_REQUEST_ERROR_URI
                )
        );
    }
}
