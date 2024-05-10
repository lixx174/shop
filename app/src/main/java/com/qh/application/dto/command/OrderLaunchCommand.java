package com.qh.application.dto.command;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author Jinx
 */
@Getter
@Setter
public class OrderLaunchCommand {

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 商品列表
     */
    private Set<String> productIds;
}
