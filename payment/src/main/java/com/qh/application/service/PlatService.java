package com.qh.application.service;

import com.qh.application.PaymentCommand;
import org.springframework.stereotype.Service;

/**
 * @author Jinx
 */
@Service
public class PlatService {

    public void pay(PaymentCommand command) {

        if (true) {
            throw new UnsupportedOperationException("test fallback");
        }

        System.out.println("command = " + command);
    }
}
