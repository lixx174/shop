package com.qh.infra.repository.order;

import com.qh.domain.order.Order;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * TODO  do -> domain
 *
 * @author Jinx
 */
@Mapper
public interface OrderConverter {

    Order convert(OrderDo od);

    List<Order> convert(List<OrderDo> ods);
}
