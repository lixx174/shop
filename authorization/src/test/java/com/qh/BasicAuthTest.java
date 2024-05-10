package com.qh;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author Jinx
 */
public class BasicAuthTest {

    @Test
    @DisplayName("Basic Auth 加密")
    public void auth(){
        String username = "1";
        String password = "1";

        String credentials = username + ":" + password;
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

        String authorizationHeader = "Basic " + base64Credentials;

        System.out.println("Authorization Header: " + authorizationHeader);
    }
}
