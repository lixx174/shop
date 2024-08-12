package com.qh.infra.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Table("tb_user")
public class UserDo {

    @Id
    private Integer id;

    private String name;

    private String sex;

    private String mobile;

    private String username;

    private String password;

    private String status;

    private LocalDateTime createAt;
}
