package com.mysite.sbb.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers("/favicon.ico").permitAll()
                        .requestMatchers("/resource/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/**").permitAll() // 추후 상황에 맡게 변경
                        .anyRequest().authenticated())
                .csrf((csrf) -> csrf
                        .ignoringRequestMatchers("/h2-console/**"))
                .headers((headers) -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin()));
        return http.build();
    }
}