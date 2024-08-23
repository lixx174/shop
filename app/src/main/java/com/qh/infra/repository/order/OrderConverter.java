package com.qh.infra.repository.order;

import com.qh.domain.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * TODO  do -> domain
 *
 * @author Jinx
 */
@Mapper
public interface OrderConverter {

    @Mappings({
            @Mapping(target = "id.id", source = "id"),
            @Mapping(target = "number.value", source = "number"),
            @Mapping(target = "userId.id", source = "userId"),
            @Mapping(target = "payment.status", source = "paymentStatus"),
            @Mapping(target = "payment.method", source = "paymentMethod"),
    })
    Order convert(OrderDo od);

    List<Order> convert(List<OrderDo> ods);
}
