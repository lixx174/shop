package com.qh.infra.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author Jinx
 */
public interface UserRepository extends CrudRepository<Integer, UserDo> {

    Optional<UserDo> findByMobile(String mobile);
}
