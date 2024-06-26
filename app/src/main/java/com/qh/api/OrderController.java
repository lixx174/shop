package com.qh.api;

import com.qh.application.model.Result;
import com.qh.application.model.command.OrderLaunchCommand;
import com.qh.application.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单
 *
 * @author Jinx
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("order")
public class OrderController {

    private final OrderService service;
    private final ApplicationContext context;


    /**
     * 预下单
     */
    @PostMapping("launch/prepare")
    public Result<String> prepareLaunch() {
        return Result.ok(service.prepareLaunch());
    }

    /**
     * 下单
     */
    @PostMapping("launch")
    public Result<Void> launch(@RequestBody OrderLaunchCommand command) {
        service.launch(command);
        return Result.ok();
    }
}
