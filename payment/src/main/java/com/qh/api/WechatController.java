package com.qh.api;

import com.qh.application.PaymentCommand;
import com.qh.application.WechatJsApiPaymentDto;
import com.qh.application.model.Result;
import com.qh.application.service.PaymentService;
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
@RequestMapping("wechat")
public class WechatController {

    private final PaymentService service;

    @PostMapping
    public Result<WechatJsApiPaymentDto> pay(@RequestBody PaymentCommand command) {
        return Result.ok(service.pay(command));
    }
}

