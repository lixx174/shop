package com.qh.domain.repository;

import com.qh.domain.User;
import com.qh.domain.primitive.UserId;

/**
 * @author Jinx
 */
public interface UserRepository {

    User findById(UserId id);
}
