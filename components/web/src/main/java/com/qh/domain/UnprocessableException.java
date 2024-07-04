package com.qh.domain;

/**
 * 无法处理的实体或其它 异常
 *
 * @author Jinx
 */
public class UnprocessableException extends RuntimeException {

    public UnprocessableException(String message) {
        super(message);
    }
}
