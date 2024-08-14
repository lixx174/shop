package com.qh.domain.repository.page;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author Jinx
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PageImpl<T> {

    /**
     * 总页数
     */
    private final Number pages;
    /**
     * 数据列表
     */
    private final List<T> contents;

    public static <T> PageImpl<T> of(Number pages, List<T> contents) {
        return new PageImpl<>(pages, contents);
    }
}
