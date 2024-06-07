package com.qh.domain.repository;

import com.qh.domain.User;

/**
 * @author Jinx
 */
public interface UserRepository {

    User findById(Integer id);
}
