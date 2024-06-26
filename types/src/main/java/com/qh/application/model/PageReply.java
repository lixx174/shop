package com.qh.application.model;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * 分页响应模型
 *
 * @author jinx
 */
@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PageReply<T> {

    /**
     * 当前页码
     */
    private final Number current;
    /**
     * 分页大小
     */
    private final Number size;
    /**
     * 总页数
     */
    private final Number pages;
    /**
     * 数据
     */
    private final List<T> records;

    public static <T> PageReply<T> of(Number current, Number size, Number pages, List<T> records) {
        return new PageReply<>(current, size, pages, records);
    }
}
