package com.qh.application.service;

import com.qh.application.PageReply;
import com.qh.application.assembler.UserAssembler;
import com.qh.application.dto.UserDto;
import com.qh.application.dto.query.UserPageQuery;
import com.qh.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jinx
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final UserAssembler assembler;


    public PageReply<UserDto> page(UserPageQuery query){
        return PageReply.of(
                query.getCurrent(),
                query.getSize(),
                1,
                null
        );
    }
}
