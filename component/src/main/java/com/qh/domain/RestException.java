package com.qh.domain;

/**
 * 远程调用异常
 *
 * @author Jinx
 */
public class RestException extends RuntimeException {

    public RestException(String message) {
        super(message);
    }
}
