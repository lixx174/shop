package com.qh.api;

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


    @PostMapping
    public Result<String> test() {
        return Result.ok("admin");
    }
}
