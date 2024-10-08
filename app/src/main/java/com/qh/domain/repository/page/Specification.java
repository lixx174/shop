package com.qh.domain.repository.page;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 规范 -> db查询条件
 * <p>
 * 为什么设计该领域？
 * - 不想每个查询 都从应用层复制一份领域查询
 *
 * @author Jinx
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Specification implements Iterable<Example> {

    private final Set<Example> examples;

    @Override
    public Iterator<Example> iterator() {
        // TODO notnull warning
        return examples.iterator();
    }

    public static Specification of(Example... examples) {
        return new Specification(new HashSet<>(Arrays.asList(examples)));
    }

    public Specification offer(String column, Object value) {
        return offer(column, value, Operation.EQUALS);
    }

    public Specification offer(String column, Object value, Operation operation) {
        // TODO  check param
        return StringUtils.hasText(column) ? offer(new Example(column, value, operation)) : this;
    }

    public Specification offer(Example example) {
        examples.add(example);
        return this;
    }
}
