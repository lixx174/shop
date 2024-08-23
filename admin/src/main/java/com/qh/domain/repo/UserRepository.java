package com.qh.domain.repo;

import com.qh.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jinx
 */
public interface UserRepository extends JpaRepository<User, Integer> {

}
