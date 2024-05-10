package com.qh.application.dto;

import com.qh.domain.primitive.Sex;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Jinx
 */
@Getter
@Setter
public class UserDto {

    private Integer id;

    private String name;

    private Sex sex;

    private String mobile;

    private String username;

    private String password;

    private LocalDateTime createAt;
}
