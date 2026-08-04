package com.mx.edu.tecdesoftware.StreamCore.api.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.edu.tecdesoftware.StreamCore.api.web.exception.ApiError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/**").permitAll()
                        // El registro de usuario debe ser público: si no, nadie puede crear
                        // la primera cuenta para después poder iniciar sesión (deadlock).
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(handling -> handling
                        // 401 - no se mandó token o el token es inválido/expiró
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpStatus.UNAUTHORIZED.value());
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                            ApiError error = new ApiError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized",
                                    "Se requiere un token JWT válido. Inicia sesión en /auth/login.");
                            objectMapper.writeValue(response.getWriter(), error);
                        })
                        // 403 - hay token válido pero no tiene permiso sobre el recurso
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpStatus.FORBIDDEN.value());
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                            ApiError error = new ApiError(HttpStatus.FORBIDDEN.value(), "Forbidden",
                                    "No tienes autorización para acceder a este recurso.");
                            objectMapper.writeValue(response.getWriter(), error);
                        })
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}