package com.qh.infra.oauth2;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.StringUtils;

/**
 * @author Jinx
 */
public class Oauth2SmsAuthenticationConverter implements AuthenticationConverter {

    public static final AuthorizationGrantType GRANT_TYPE = new AuthorizationGrantType("sms");
    private static final String ACCESS_TOKEN_REQUEST_ERROR_URI = "www.baidu.com";

    @Override
    public Authentication convert(HttpServletRequest request) {
        // grant_type (REQUIRED)
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        if (!GRANT_TYPE.getValue().equals(grantType)) {
            return null;
        }

        // mobile (REQUIRED)
        String mobile = request.getParameter("mobile");
        if (!StringUtils.hasText(mobile)) {
            throw new OAuth2AuthenticationException(
                    new OAuth2Error(
                            OAuth2ErrorCodes.INVALID_REQUEST,
                            "missing Parameter： mobile",
                            ACCESS_TOKEN_REQUEST_ERROR_URI
                    )
            );
        }

        // code (REQUIRED)
        String code = request.getParameter(OAuth2ParameterNames.CODE);
        if (!StringUtils.hasText(code)) {
            throw new OAuth2AuthenticationException(
                    new OAuth2Error(
                            OAuth2ErrorCodes.INVALID_REQUEST,
                            "missing Parameter： %s".formatted(OAuth2ParameterNames.CODE),
                            ACCESS_TOKEN_REQUEST_ERROR_URI
                    )
            );
        }

        return new Oauth2SmsAuthenticationToken(SecurityContextHolder.getContext().getAuthentication(), mobile, code);
    }
}
