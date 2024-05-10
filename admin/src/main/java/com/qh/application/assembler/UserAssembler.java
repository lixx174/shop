package com.qh.application.assembler;

import com.qh.application.dto.UserDto;
import com.qh.domain.User;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Jinx
 */
@Mapper
public interface UserAssembler {

    UserDto assemble(User user);

    List<UserDto> assemble(List<User> users);
}
