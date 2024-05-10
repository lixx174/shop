package com.qh.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 商品
 *
 * @author Jinx
 */
@Getter
@Setter
public class ProductDto {

    private String id;

    private String name;

    private String desc;

    private BigDecimal price;
}
