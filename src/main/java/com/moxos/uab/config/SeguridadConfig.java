package com.moxos.uab.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.multipart.support.MultipartFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SeguridadConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandle;

    @Bean
    public BCryptPasswordEncoder bcryptEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }

    @Bean
    public AuthenticationProvider authProvider() {
        //CustomAuthenticationProvider provider = new CustomAuthenticationProvider();
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public ProviderManager providerManager() {
        List<AuthenticationProvider> list = new ArrayList<AuthenticationProvider>();
        list.add(authProvider());
        return new ProviderManager(list);
    }

    @Bean
    public UsernamePasswordAuthenticationFilter authenticationProcessingFilter() {
        CapchatUsernamePasswordAuthenticationFilter capchat = new CapchatUsernamePasswordAuthenticationFilter();
        capchat.setAuthenticationManager(providerManager());
        capchat.setAuthenticationDetailsSource(new CustomWebAuthenticationDetailsSource());
        capchat.setPasswordParameter("clave");
        capchat.setUsernameParameter("apodo");
        capchat.setAuthenticationSuccessHandler(customAuthenticationSuccessHandle);
        capchat.setAuthenticationFailureHandler(authenticationFailureHandler());
        capchat.setFilterProcessesUrl("/authentication");
        return capchat;
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint("/login");
    }

    @Bean
    public MultipartFilter multipartFilter() {
        MultipartFilter multipartFilter = new MultipartFilter();
        multipartFilter.setMultipartResolverBeanName("multipartResolver");
        return multipartFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/WEB-INF/**").permitAll()
                        .requestMatchers("/login").permitAll()
//                        .requestMatchers("/elegir-rol").permitAll()
//                        .requestMatchers("/cambiar-rol").permitAll()
//                        .requestMatchers("/index").permitAll()
                        .requestMatchers("/expired").permitAll()
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/captcha.jpg/**").permitAll()
                        .requestMatchers("/recovery").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::newSession)
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .invalidSessionUrl("/logout")
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                        .expiredUrl("/expired"))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JSESSIONID"))
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                        .permissionsPolicy(permissions -> permissions
                                .policy("geolocation=(self)")))
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage("/error")
                )
                .addFilterAfter(authenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(multipartFilter(), SecurityContextHolderFilter.class)
                .httpBasic(httpBasic -> httpBasic
                        .authenticationEntryPoint(authenticationEntryPoint())
                ).build();
    }
}
