package com.qh.domain.primitive;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Jinx
 */
@Getter
@RequiredArgsConstructor
public class OrderStatus {

    public static final OrderStatus WAIT_PAY = new OrderStatus("1");
    public static final OrderStatus PAID = new OrderStatus("2");
    public static final OrderStatus CANCELLED = new OrderStatus("3");
    public static final OrderStatus FINISHED = new OrderStatus("2");

    private final String value;
}
