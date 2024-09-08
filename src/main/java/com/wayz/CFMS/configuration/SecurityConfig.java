package com.wayz.CFMS.configuration;

import com.wayz.CFMS.models.subModels.UserRole;
import com.wayz.CFMS.services.employee.auth.impl.UserAuthServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final UserAuthServiceImpl userAuthService;

    public SecurityConfig(UserAuthServiceImpl userAuthService) {
        this.userAuthService = userAuthService;
    }

    /**
     * Реализация кастомного userDetails
     *
     * @return кастомный UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return userAuthService;
    }

    /**
     * Цепочка фильтров авторизации
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/auth/**").anonymous()
                        .requestMatchers("/user/**").hasRole(UserRole.EMPLOYEE.name())
                        .requestMatchers("/admin/**").hasRole(UserRole.ADMINISTRATOR.name())
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Без сессий для REST API
                )
                .httpBasic(Customizer.withDefaults()) // Используем HTTP Basic для аутентификации
                .formLogin(AbstractHttpConfigurer::disable) // Отключаем стандартную форму логина
                .logout(AbstractHttpConfigurer::disable); // Отключаем стандартный logout

        return http.build();
    }


    /**
     * Настройка кодирования пароля
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Кодирование пароля
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Определяем AuthenticationManager как бин, чтобы он мог быть внедрен в сервисы
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
