package com.example.memberservice.security.config;

import com.example.memberservice.member.service.MemberService;
import com.example.memberservice.security.filter.AuthenticationFilter;
import com.example.memberservice.security.jwt.JwtTokenizer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberService memberService;
    private final JwtTokenizer jwtTokenizer;
    private final BCryptPasswordEncoder encoder;
    private final ObjectPostProcessor<Object> objectPostProcessor;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(AbstractHttpConfigurer::disable)
                .addFilter(getAuthenticationFilter())
                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/actuator/*").permitAll()
//                        .requestMatchers("/**").permitAll()
                        .anyRequest().permitAll()
                );
        return httpSecurity.build();
    }

    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(encoder);
        return auth.build();
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(memberService, jwtTokenizer);
        AuthenticationManagerBuilder builder = new AuthenticationManagerBuilder(objectPostProcessor);
        authenticationFilter.setAuthenticationManager(authenticationManager(builder));
        return authenticationFilter;
    }
}
