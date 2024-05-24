package com.qh.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jinx
 */
@RestController
@RequiredArgsConstructor
public class CallbackController {


    @PostMapping
    public void wechatPaymentCallback() {

    }
}
