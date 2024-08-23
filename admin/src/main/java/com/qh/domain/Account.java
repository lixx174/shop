package com.qh.domain;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jinx
 */
@Getter
@Setter
@Table(name = "tb_account")
public class Account {

    @Id
    private Integer id;

    private String mobile;

    private String username;

    private String password;
}
