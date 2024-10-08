package com.qh.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jinx
 */
@Getter
@Setter
@Entity
@Table(name = "tb_account")
public class Account {

    @Id
    private Integer id;

    private String mobile;

    private String username;

    private String password;
}
