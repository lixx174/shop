package com.qh.application.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页请求基础模型
 *
 * @author jinx
 */
@Getter
@Setter
public class PageQuery {

    /**
     * 当前页码(默认1)
     */
    private Number current = 1;
    /**
     * 分页大小(默认10)
     */
    private Number size = 10;
}
