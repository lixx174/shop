package com.qh.domain;

import java.math.BigDecimal;

/**
 * 商品变体 - 相对于 {@link Product} 它是更具体的原子商品
 *
 * @author Jinx
 */
public class ProductVariant{

    private Integer id;

    private Product product;

    private BigDecimal price;

    /**
     * 库存
     */
    private Integer inventory;
}
