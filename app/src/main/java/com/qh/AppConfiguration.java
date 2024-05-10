package com.qh;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jinx
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "shop.app")
public class AppConfiguration {

    private String version;
}
