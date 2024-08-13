package com.kitri.bark_meow_party_server.config;

import com.kitri.bark_meow_party_server.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    private final CustomExceptionHandler customExceptionHandler;

    public SecurityConfig(CustomExceptionHandler customExceptionHandler) {
        this.customExceptionHandler = customExceptionHandler;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("select username, password, enabled from user where username=?");
        userDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT u.username, a.authority FROM authorities a " +
                        "JOIN user u ON a.user_id = u.id WHERE u.username = ?");

        return userDetailsManager;
    }


//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin123")
//                .roles("ADMIN")
//                .build();
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("user123")
//                .roles("USER")
//                .build();
//        UserDetails test = User.withDefaultPasswordEncoder()
//                .username("test")
//                .password("test")
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user, test);
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // CSRF 보호 비활성화
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/inquiry/answer/{inquiryId}/create").hasRole("ADMIN")
                                .requestMatchers("api/inquiry/answer/list").hasRole("ADMIN")
                                .requestMatchers("/api/inquiry/answer/{id}/update").hasRole("ADMIN")
                                .requestMatchers("/api/inquiry/answer/{id}/delete").hasRole("ADMIN")
                                .requestMatchers("/api/qa/{qaId}/answers/create").hasRole("DOCTOR")
                                .requestMatchers("/api/qa/{qaId}/answers/{answerId}/update").hasRole("DOCTOR")
                                .requestMatchers("/api/qa/{qaId}/answers/{answerId}/delete").hasRole("DOCTOR")
                                .requestMatchers("/api/auth/login").permitAll()
                                .requestMatchers("/api/doctor/login").permitAll()
                                .requestMatchers("/api/auth/signup").permitAll()
                                .requestMatchers("/api/doctor/signup").permitAll()
                                .requestMatchers("/api/auth/check-username").permitAll()
                                .requestMatchers("/api/auth/check-nickname").permitAll()
//                                .requestMatchers("/**").permitAll()  // 인증이 필요 없는 엔드포인트
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .authenticationEntryPoint(customExceptionHandler)
                                .accessDeniedHandler(customExceptionHandler)
                )
                .cors(withDefaults())
                .formLogin(withDefaults()); // 스프링 시큐리티 기본 로그인 페이지 사용

        return http.build();
    }

}
