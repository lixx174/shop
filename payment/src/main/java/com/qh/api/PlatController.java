package com.qh.api;

import com.qh.application.PaymentCommand;
import com.qh.application.model.Result;
import com.qh.application.service.PlatService;
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
@RequestMapping("/plat")
public class PlatController {

    private final PlatService service;

    /**
     * 统一支付
     *
     * @param command 支付模型
     * @return void
     */
    @PostMapping
    public Result<Void> pay(@RequestBody PaymentCommand command) {
        return Result.ok(() -> service.pay(command));
    }
}

