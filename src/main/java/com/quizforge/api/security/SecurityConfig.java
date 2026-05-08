package com.quizforge.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Disable CSRF (Cross-Site Request Forgery) because we use Tokens, not Cookies
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Define the public and private doors
                .authorizeHttpRequests(auth -> auth
                        // Leave the login and registration endpoints completely public
                        .requestMatchers("/api/auth/**").permitAll()

                        // Leave Swagger UI public so you can test!
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                        // Example of locking a specific route to Admins ONLY
                        .requestMatchers("/api/exams/upload").hasRole("ADMIN")

                        // Every other endpoint requires a valid token
                        .anyRequest().authenticated()
                )

                // 3. Turn off Spring's default "Coat Check" session memory
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 4. Put our custom Bouncer strictly BEFORE the default Spring Security filter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}