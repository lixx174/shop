package com.qh.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.function.Predicate;

/**
 * 用户信息
 * <p>
 * 请求经过鉴权后会将用户信息封装为该对象放入request
 *
 * @author Jinx
 */
@Getter
@Setter
public class UserDetail {

    private Integer id;
    private String name;
    private Set<String> permissions;

    public boolean isAuthorized(Predicate<String> predicate) {
        return permissions.stream().anyMatch(predicate);
    }
}
