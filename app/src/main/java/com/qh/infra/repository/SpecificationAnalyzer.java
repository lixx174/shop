package com.qh.infra.repository;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.qh.domain.repository.page.Example;
import com.qh.domain.repository.page.Operation;
import com.qh.domain.repository.page.Specification;
import org.springframework.stereotype.Component;

/**
 * 规范解析器  针对mybatis  如果需要适配jpa 需要扩展
 *
 * @author Jinx
 */
@Component
public class SpecificationAnalyzer {

    public <T> Wrapper<T> analyze(Specification specification) {
        QueryWrapper<T> qw = Wrappers.query();

        for (Example example : specification) {
            doAnalyze(example, qw);
        }

        return qw;
    }

    private void doAnalyze(Example example, QueryWrapper<?> qw) {
        if (example.getOperation() == Operation.EQUALS) {
            qw.eq(example.getColumn(), example.getValue());
        }
    }
}
