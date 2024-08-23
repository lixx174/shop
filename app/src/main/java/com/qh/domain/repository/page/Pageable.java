package com.qh.domain.repository.page;

/**
 * @author Jinx
 */
public interface Pageable {

    /**
     * 当前页数
     *
     * @return 页数 limit x
     */
    Number getCurrent();

    /**
     * 每页数据条数
     *
     * @return 条数 limit 90, x
     */
    Number getSize();

    /**
     * 获取规范
     * TODO 该规范应该单独为一个接口 不属于pageable自己
     *
     * @return 查询条件
     */
    default Specification getSpecification() {
        return Specification.of();
    }
}
