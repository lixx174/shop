package com.qh.infra.repository.order;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qh.domain.order.Order;
import com.qh.domain.order.OrderRepository;
import com.qh.domain.repository.page.PageImpl;
import com.qh.domain.repository.page.Pageable;
import com.qh.infra.repository.SpecificationAnalyzer;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author Jinx
 */
@Repository
@RequiredArgsConstructor
public class DefaultOrderRepository implements OrderRepository {

    private final OrderMapper mapper;
    private final OrderConverter converter;
    private final SpecificationAnalyzer analyzer;

    @Override
    public PageImpl<Order> findAll(Pageable pageable) {
        Page<OrderDo> page = mapper.selectPage(
                Page.of(pageable.getCurrent().longValue(), pageable.getSize().longValue()),
                analyzer.analyze(pageable.getSpecification())
        );

        return PageImpl.of(page.getPages(), converter.convert(page.getRecords()));
    }

    @Mapper
    interface OrderMapper extends BaseMapper<OrderDo> {

    }
}
