package com.qh.domain;

import lombok.Getter;
import org.springframework.util.Assert;

import java.util.UUID;

/**
 * 手机
 * FIXME 该领域是否过于抽象 无边界
 *
 * @author Jinx
 */
@Getter
public class Mobile {

    private final UUID id = UUID.randomUUID();

    private String number;

    public Mobile(String number) {
        Assert.hasText(number, "mobile number must have value");
        Assert.isTrue(number.matches("1[3456789]\\d{9}"), "illegal mobile number -> %s".formatted(number));

        this.number = number;
    }
}
