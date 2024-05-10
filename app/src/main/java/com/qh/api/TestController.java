package com.qh.api;

import com.qh.AppConfiguration;
import com.qh.application.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * test
 *
 * @author Jinx
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("test")
public class TestController {

    private final AppConfiguration configuration;

    @PostMapping
    public Result<String> test() {
        System.out.println(configuration);
        return Result.ok("app");
    }
}
