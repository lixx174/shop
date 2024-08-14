package com.qh.domain.order;

import com.qh.domain.repository.page.PageImpl;
import com.qh.domain.repository.page.Pageable;

/**
 * @author Jinx
 */
public interface OrderRepository {

    PageImpl<Order> findAll(Pageable pageable);
}
