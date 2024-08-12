package com.qh.infra.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Jinx
 */
@Repository
public interface UserRepository extends CrudRepository<UserDo, Integer> {

    Optional<UserDo> findByMobile(String mobile);
}
