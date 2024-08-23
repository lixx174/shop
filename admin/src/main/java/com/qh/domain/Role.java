package com.qh.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author Jinx
 */
@Getter
@Setter
@Entity
@Table(name = "tb_role")
public class Role {

    @Id
    private Integer id;

    private String name;

    private Set<Authority> authorities;
}
