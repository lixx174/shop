package com.qh.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jinx
 */
@Getter
@Setter
@Entity
@Table(name = "tb_authority")
public class Authority {

    private Integer id;

    private String name;
}
