package com.qh.infra.repository;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDo {

    private Integer id;

    private String name;

    private String sex;

    private String mobile;

    private String username;

    private String password;

    private String status;

    private LocalDateTime createAt;
}
