package com.qh.application.model.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author Jinx
 */
@Getter
@Setter
public class OrderLaunchCommand {

    @JsonCreator
    public OrderLaunchCommand(String orderId, LocalDate localDate, Set<String> productIds) {
        Assert.notNull(orderId, "orderId not null");
        Assert.notNull(localDate, "localDate not null");
        Assert.notNull(productIds, "productIds not null");

        this.orderId = orderId;
        this.localDate = localDate;
        this.productIds = productIds;
    }

    /**
     * 订单id
     */
    private String orderId;
    private LocalDate localDate;

    /**
     * 商品列表
     */
    private Set<String> productIds;
}
