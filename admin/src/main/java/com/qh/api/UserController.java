package com.qh.api;

import com.qh.application.PageReply;
import com.qh.application.Result;
import com.qh.application.dto.UserDto;
import com.qh.application.dto.query.UserPageQuery;
import com.qh.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户管理
 *
 * @author Jinx
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;


    /**
     * 分页
     */
    @GetMapping("/page")
    public Result<PageReply<UserDto>> page(UserPageQuery query) {
        return Result.ok(service.page(query));
    }
}


