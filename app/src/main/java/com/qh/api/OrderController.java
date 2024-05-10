package com.qh.api;

import com.qh.application.Result;
import com.qh.application.dto.command.OrderLaunchCommand;
import com.qh.application.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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


    /**
     * 预下单
     */
    @GetMapping("launch/preparation")
    public Result<String> prepareLaunch() {
        return Result.ok(service.prepareLaunch());
    }

    /**
     * 下单
     */
    @GetMapping("launch")
    public Result<Void> launch(@RequestBody OrderLaunchCommand command) {
        service.launch(command);
        return Result.ok();
    }
}
