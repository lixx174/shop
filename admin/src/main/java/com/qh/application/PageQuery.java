package com.qh.application;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页请求基础模型  所有分页模型都需要继承它
 * <p>
 * 具体模型看子类
 *
 * @author jinx
 */
@Getter
@Setter
public class PageQuery {

    /**
     * 当前页码(默认1)
     */
    private Integer current = 1;
    /**
     * 分页大小(默认10)
     */
    private Integer size = 10;
}
