package com.qh.domain;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Jinx
 */
@Getter
@Setter
@Table(name = "tb_user")
public class User {

    @Id
    private Integer id;

    private String name;

    private Sex sex;

    private LocalDate birthday;

    private Status status;

    private Account account;
}
