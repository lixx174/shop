package com.qh.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

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

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private Status status;

    /**
     * TODO 1.target还需要设置？ 2.级联属性？ 3.mappedBy 是哪来干嘛的
     */
    @OneToOne(fetch = FetchType.LAZY)
    private Account account;

    @ManyToMany
    @JoinTable(
            name = "tb_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}
