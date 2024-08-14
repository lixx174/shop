package com.qh.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qh.application.model.UserInfo;
import lombok.SneakyThrows;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * FIXME 该类是否应该放在应用层
 *
 * @author Jinx
 */
public class SecuritySupport {

    private static final ObjectMapper om = new ObjectMapper();

    @SneakyThrows
    public static Integer getUserId() {
        String userinfo = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest()
                .getHeader("userinfo");

        UserInfo userInfo = om.readValue(userinfo, UserInfo.class);

        return userInfo.getId();
    }
}
