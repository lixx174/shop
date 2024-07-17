package com.qh;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jinx
 */
@Configuration
@ConfigurationProperties(prefix = "firewall")
public class GatewayConfiguration {


    public static class Black {

    }
}
