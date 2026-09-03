package com.easybytes.easystore.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class EasyStoreSecurityConfig {

    private final List<String> publicPaths;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //options: permitAll, authenticated, denyAll, hasRole
        http.authorizeHttpRequests((requests) -> {
            publicPaths.forEach(path -> requests.requestMatchers(path).permitAll());
            requests.anyRequest().authenticated();
        });
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }
}
