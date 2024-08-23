package com.qh.api;

import com.qh.application.model.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jinx
 */
@RestController
@RequestMapping("user")
public class UserController {


    @PostMapping()
    public Result<Void> cr(){
        return null;
    }
}
