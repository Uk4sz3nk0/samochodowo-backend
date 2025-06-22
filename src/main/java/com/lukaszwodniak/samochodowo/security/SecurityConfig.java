package com.lukaszwodniak.samochodowo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfig
 * <br>
 * Created on: 2025-06-20
 *
 * @author Łukasz Wodniak
 */

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private static final String[] ENDPOINTS_WHITE_LIST = {
            "/cars/**"
    };

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authManager ->
                        authManager.requestMatchers(ENDPOINTS_WHITE_LIST)
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
