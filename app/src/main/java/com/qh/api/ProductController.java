package com.qh.api;

import com.qh.application.model.PageQuery;
import com.qh.application.model.PageReply;
import com.qh.application.model.ProductDto;
import com.qh.application.model.Result;
import com.qh.application.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品
 *
 * @author Jinx
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("product")
public class ProductController {

    private final ProductService service;

    /**
     * 分页
     */
    @GetMapping("page")
    public Result<PageReply<ProductDto>> page(PageQuery query) {
        return Result.ok(service.page(query));
    }
}
