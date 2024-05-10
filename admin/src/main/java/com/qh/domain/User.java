package com.qh.domain;

import com.qh.domain.primitive.Sex;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Jinx
 */

@Getter
@Setter
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    private Integer id;

    private String name;

    @Enumerated
    private Sex sex;

    private String mobile;

    private String username;

    private String password;

    private LocalDateTime createAt;
}
