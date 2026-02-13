package com.ajemi.barber.Ta7li9_app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ajemi.barber.Ta7li9_app.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF hit khdamin b JWT
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Ma-ghadi-ch n-khbiw session f l-server
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll() // Register w Login m-7loulin l-kolchi
                .anyRequest().authenticated() // Ay 7aja khora m-7miya
            );

        // Zid l-Filter dyalna qbel l-Filter dyal Spring s-standard
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //     http
    //         .csrf(csrf -> csrf.disable()) // Disable CSRF f l-bdaya bach mat-mrtkch f les tests
    //         .authorizeHttpRequests(auth -> auth
    //             .anyRequest().authenticated() // Ay haja khassha t-login
    //         )
    //         .oauth2Login(withDefaults()); // F33el Google Login
            
    //     return http.build();
    // }
}
