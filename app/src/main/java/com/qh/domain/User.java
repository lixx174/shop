package com.qh.domain;

import com.qh.domain.primitive.UserId;
import com.qh.domain.primitive.UserName;

import java.util.List;

/**
 * 用户
 *
 * @author Jinx
 */
public class User {

    private UserId id;

    private UserName name;

    private List<UserCoupon> ucs;
}
