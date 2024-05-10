package com.qh.infra.repository;

import com.qh.domain.User;
import com.qh.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jinx
 */
@Repository
public interface JpaUserRepository extends UserRepository, JpaRepository<User, Integer>{

}
