package com.qh.infra.oauth2;

import com.qh.domain.RedisClient;
import com.qh.infra.redis.SmsCodeKey;
import com.qh.infra.redis.UserDetailKey;
import com.qh.infra.repository.UserDo;
import com.qh.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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


    /**
     * 通过认证信息加载用户
     *
     * @param authenticationToken 认证信息
     * @return userDetail
     */
    public UserDetails authenticateAndLoadUserDetail(Oauth2SmsAuthenticationToken authenticationToken) {
        String smsCode = redisClient.get(new SmsCodeKey(authenticationToken.getMobile()), String.class);

        if (StringUtils.hasText(smsCode) && smsCode.equals(authenticationToken.getSmsCode())) {
            UserDetails userDetails = redisClient.get(
                    new UserDetailKey(authenticationToken.getMobile()), UserDetails.class
            );

            if (userDetails == null) {
                Optional<UserDo> user = userRepo.findByMobile(authenticationToken.getMobile());
                if (user.isPresent()) {
                    return null;
                } else {
                    throw new UsernameNotFoundException("invalid mobile: %s".formatted(authenticationToken.getMobile()));
                }
            }

            return userDetails;
        }

        throw new OAuth2AuthenticationException(
                new OAuth2Error(
                        OAuth2ErrorCodes.INVALID_REQUEST,
                        "sms code not match",
                        ACCESS_TOKEN_REQUEST_ERROR_URI
                )
        );
    }
}
