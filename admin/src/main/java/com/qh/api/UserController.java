package com.qh.api;

import com.qh.application.UserCreateCommand;
import com.qh.application.UserService;
import com.qh.application.model.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jinx
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService service;

    @PostMapping
    public Result<Void> create(@RequestBody UserCreateCommand command){
        return Result.ok(() -> service.create(command));
    }
}
