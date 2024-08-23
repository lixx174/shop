package com.qh.application;

import com.qh.application.assembler.UserAssembler;
import com.qh.domain.User;
import com.qh.domain.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jinx
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserAssembler assembler;
    private final UserRepository repo;

    public void create(UserCreateCommand command){
        User user = assembler.assemble(command);
        repo.save(user);
    }
}
