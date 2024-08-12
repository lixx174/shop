package com.qh.infra;

import com.qh.domain.Mobile;
import com.qh.domain.MobileClient;
import com.qh.domain.ShortMessage;
import org.springframework.stereotype.Component;

/**
 * @author Jinx
 */
@Component
public class DefaultMobileClient implements MobileClient {
    @Override
    public void sendShortMessage(Mobile mobile, ShortMessage message) {
        // TODO do send
        System.out.println(" %s -> %s".formatted(mobile.getNumber(), message.getValue()));
    }
}
