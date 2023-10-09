package com.suw0n.tabmark.auth.infrastructure.security.config;

import com.suw0n.tabmark.auth.infrastructure.exception.filter.ExceptionFilter;
import com.suw0n.tabmark.auth.infrastructure.security.handler.CustomAccessDeniedHandler;
import com.suw0n.tabmark.auth.infrastructure.security.oauth.handler.OAuthFailureHandler;
import com.suw0n.tabmark.auth.infrastructure.security.oauth.handler.OAuthSuccessHandler;
import com.suw0n.tabmark.auth.infrastructure.jwt.filter.JwtExceptionFilter;
import com.suw0n.tabmark.auth.infrastructure.jwt.filter.JwtFilter;
import com.suw0n.tabmark.auth.infrastructure.security.oauth.service.OAuthMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuthMemberService oAuthMemberService;
    private final OAuthSuccessHandler oAuthSuccessHandler;
    private final OAuthFailureHandler oAuthFailureHandler;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final JwtFilter jwtFilter;
    private final JwtExceptionFilter jwtExceptionFilter;
    private final ExceptionFilter exceptionFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors()
                .and()
                .csrf().disable()
                .addFilterBefore(exceptionFilter, OAuth2LoginAuthenticationFilter.class)
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, JwtFilter.class)

                .authorizeHttpRequests()
                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/swagger-ui/**", "/v3/**").permitAll()
                .requestMatchers("/auth/refresh").permitAll()

                .requestMatchers("/member/**").authenticated()
                .requestMatchers("/tabmark/**").authenticated()
                .requestMatchers("/tag/**").authenticated()
                .requestMatchers("/attachment/**").authenticated()

                .anyRequest().authenticated()

                .and()
                .formLogin().disable()
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))

                .and()
                .oauth2Login()
                .successHandler(oAuthSuccessHandler)
                .failureHandler(oAuthFailureHandler)
                .userInfoEndpoint()
                .userService(oAuthMemberService);

        return http.build();
    }

}