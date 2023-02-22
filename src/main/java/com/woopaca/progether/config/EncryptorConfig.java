package com.woopaca.progether.config;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptorConfig {

    @Bean
    public BCrypt bcryptEncoder() {
        return new BCrypt();
    }
}
