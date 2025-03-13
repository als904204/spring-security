package com.easybytes.springsecsection3.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    // SpringBootWebSecurityConfiguration.class
    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        http.authorizeHttpRequests((requests) -> requests
            .requestMatchers("/myAccount", "/myBalance", "/myCards", "/myLoans").authenticated()
            .requestMatchers("/notices", "/contact", "/error").permitAll());

        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user").password("{bcrypt}$2a$12$PI/9yeK1u9xmmVEG1RQyzupCJeHu7zattKuwzrYJvgm.I2wWoWWUe").authorities("read").build();
        UserDetails admin = User.withUsername("admin").password("{bcrypt}$2a$12$PI/9yeK1u9xmmVEG1RQyzupCJeHu7zattKuwzrYJvgm.I2wWoWWUe").authorities("admin").build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // return new BCryptPasswordEncoder() 안하는 이유
        // Spring Security 가 언젠간 권장 사항을 BCryptPasswordEncoder() 말고 다른 걸로 변경할수도 있음
        // 아래 팩토리는 시큐리티가 권장하는걸 기본값으로 유연성 제공 함
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }




}
