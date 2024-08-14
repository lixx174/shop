package com.qh.application.assembler;

import com.qh.application.model.OrderDto;
import com.qh.domain.order.Order;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author Jinx
 */
@Mapper
public interface OrderAssembler {

    OrderDto assemble(Order order);

    List<OrderDto> assemble(List<Order> orders);
}
