package com.qh.application.model.query;

import com.qh.application.SecuritySupport;
import com.qh.application.model.PageQuery;
import com.qh.domain.repository.page.Pageable;
import com.qh.domain.repository.page.Specification;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jinx
 */
@Getter
@Setter
public class OrderPageQuery extends PageQuery implements Pageable {
    /**
     * 订单号
     */
    private String number;
    /**
     * 订单状态
     */
    private String status;

    @Override
    public Specification getSpecification() {
        return Pageable.super.getSpecification()
                .offer("number", number)
                .offer("status", status)
                .offer("user_id", SecuritySupport.getUserId());
    }
}
