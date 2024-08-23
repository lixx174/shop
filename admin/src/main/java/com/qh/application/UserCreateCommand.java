package com.qh.application;

import com.qh.domain.Sex;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author Jinx
 */
@Getter
@Setter
public class UserCreateCommand {

    private String name;

    private Sex sex;

    private LocalDate birthday;

    private String mobile;

    private String username;

    private String password;

    private Set<String> roles;
}
