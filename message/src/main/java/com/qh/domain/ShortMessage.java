package com.qh.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

/**
 * 短信
 *
 * @author Jinx
 */
@Getter
public class ShortMessage implements Message {

    private UUID id;

    private String value;

    private LocalDateTime expireAt;

    public ShortMessage() {
        this(LocalDateTime.now().plusMinutes(5));
    }

    public ShortMessage(LocalDateTime expireAt) {
        if (expireAt == null) {
            throw new IllegalArgumentException("expireAt must have value");
        }
        if (expireAt.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("invalid expireTime -> %s".formatted(expireAt));
        }

        id = UUID.randomUUID();
        value = String.format("%06d", new Random().nextInt(1000000));
        this.expireAt = expireAt;
    }
}
