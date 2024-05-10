package com.qh.application.dto.query;

import com.qh.application.PageQuery;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Jinx
 */
@Getter
@Setter
public class UserPageQuery extends PageQuery {

    /**
     * 姓名 模糊匹配
     */
    private String name;
}
