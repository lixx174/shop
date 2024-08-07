package com.qh.infra.oauth2;

import com.qh.domain.RedisClient;
import com.qh.infra.repository.UserDo;
import com.qh.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

import static com.qh.infra.oauth2.Oauth2SmsAuthenticationProvider.ACCESS_TOKEN_REQUEST_ERROR_URI;

/**
 * @author Jinx
 */
@Component
@RequiredArgsConstructor
public class UserDetailService {

    private final RedisClient redisClient;
    private final UserRepository userRepo;

    public UserDetails loadUserByAuthenticationToken(Oauth2SmsAuthenticationToken authenticationToken) {
        String smsCode = redisClient.get(() -> redisKey(authenticationToken.getMobile()), String.class);

        if (StringUtils.hasText(smsCode) && smsCode.equals(authenticationToken.getSmsCode())) {
            Optional<UserDo> user = userRepo.findByMobile(authenticationToken.getMobile());
            if (user.isPresent()) {
                // TODO userDetail 做成领域？
                // gateway 也会依赖该模型
                return null;
            } else {
                throw new OAuth2AuthenticationException(
                        new OAuth2Error(
                                OAuth2ErrorCodes.INVALID_REQUEST,
                                "invalid mobile : %s".formatted(authenticationToken.getMobile()),
                                ACCESS_TOKEN_REQUEST_ERROR_URI
                        )
                );
            }
        }

        throw new OAuth2AuthenticationException(
                new OAuth2Error(
                        OAuth2ErrorCodes.INVALID_REQUEST,
                        "sms code not match",
                        ACCESS_TOKEN_REQUEST_ERROR_URI
                )
        );
    }

    private String redisKey(String mobile) {
        return "shop:authorization:sms:" + mobile;
    }
}
