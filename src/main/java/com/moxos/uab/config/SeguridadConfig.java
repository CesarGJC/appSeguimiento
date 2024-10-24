package com.moxos.uab.config;

import com.moxos.uab.config.filters.CaptchaUsernamePasswordAuthenticationFilter;
import com.moxos.uab.config.filters.NonceHeaderWriter;
import com.moxos.uab.config.handler.CustomAuthenticationFailureHandler;
import com.moxos.uab.config.handler.CustomAuthenticationSuccessHandler;
import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SeguridadConfig {

    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandle;
    private final CustomUserDetailsService userDetailsService;
    private final CustomAuthenticationFailureHandler authenticationFailureHandler;

    public SeguridadConfig(CustomUserDetailsService userDetailsService, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandle, CustomAuthenticationFailureHandler authenticationFailureHandler) {
        this.userDetailsService = userDetailsService;
        this.customAuthenticationSuccessHandle = customAuthenticationSuccessHandle;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Bean
    public BCryptPasswordEncoder bcryptEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(bcryptEncoder());
        return provider;
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint("/login");
    }

    @Bean
    public UsernamePasswordAuthenticationFilter authenticationProcessingFilter(AuthenticationManager authenticationManager) {
        CaptchaUsernamePasswordAuthenticationFilter captchaFilter = new CaptchaUsernamePasswordAuthenticationFilter(authenticationManager);
        captchaFilter.setPasswordParameter("clave");
        captchaFilter.setUsernameParameter("apodo");
        captchaFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandle);
        captchaFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        captchaFilter.setFilterProcessesUrl("/authentication");
        captchaFilter.setAllowSessionCreation(true);
        return captchaFilter;
    }

    @Bean
    public MultipartFilter multipartFilter() {
        MultipartFilter multipartFilter = new MultipartFilter();
        multipartFilter.setMultipartResolverBeanName("multipartResolver");
        return multipartFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authProvider())
                .build();
    }
    @Bean
    public MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector).servletPath("/spring-mvc");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf((csrf) -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .sessionManagement(session -> session
                        .sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::migrateSession)
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Crear sesión solo si es necesario
                        .invalidSessionUrl("/session-invalid") // Redirigir si la sesión es inválida
                        .maximumSessions(1) // Limitar a 1 sesión por usuario
                        .maxSessionsPreventsLogin(true) // Evitar nuevos inicios de sesión si se supera el límite
                        .expiredUrl("/session-expired") // Redirigir cuando la sesión expira
                )
                .logout(logout -> logout
                        .permitAll()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .deleteCookies("JSESSIONID"))
                .headers(headers -> headers
                        //.addHeaderWriter(new NonceHeaderWriter())
                        .xssProtection(xssProtection -> xssProtection.headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK))
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                        .permissionsPolicy(permissions -> permissions
                                .policy("geolocation=(self)"))
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/expired")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/public/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/captcha.jpg/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/recovery")).permitAll()
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling((exception) -> exception
                        .authenticationEntryPoint(authenticationEntryPoint()).accessDeniedPage("/accedd-denied"))
                .addFilterAfter(authenticationProcessingFilter(authenticationManager(http)), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(multipartFilter(), SecurityContextHolderFilter.class)
                //.addFilterBefore(new NonceFilter(), WebAsyncManagerIntegrationFilter.class)
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                ).httpBasic(Customizer.withDefaults())
                .securityContext((securityContext) -> securityContext.requireExplicitSave(false))
                .build();
    }
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
