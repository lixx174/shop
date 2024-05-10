package com.qh.infra.oauth2;

import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;

import static com.qh.infra.oauth2.Oauth2SmsAuthenticationConverter.GRANT_TYPE;

/**
 * @author Jinx
 */
@Getter
public class Oauth2SmsAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {

    private final String mobile;

    private final String smsCode;

    public Oauth2SmsAuthenticationToken(Authentication clientPrincipal, String mobile, String smsCode) {
        super(GRANT_TYPE, clientPrincipal, null);
        this.mobile = mobile;
        this.smsCode = smsCode;
    }
}
