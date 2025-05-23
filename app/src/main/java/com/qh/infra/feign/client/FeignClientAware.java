package com.qh.infra.feign.client;

import java.io.Serializable;

/**
 * 标记接口 - 用于feign进行client扫描
 *
 * @author Jinx
 */
public interface FeignClientAware {

    Serializable version();
}
