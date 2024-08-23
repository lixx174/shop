package com.qh.application.assembler;

import com.qh.application.UserCreateCommand;
import com.qh.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author Jinx
 */
@Mapper
public interface UserAssembler {

    /**
     *  TODO Set<String> TO Set<Role>
     */
    @Mappings({
            @Mapping(target = "account.username", source = "username"),
            @Mapping(target = "account.password", source = "password"),
            @Mapping(target = "account.mobile", source = "mobile"),
    })
    User assemble(UserCreateCommand command);
}
