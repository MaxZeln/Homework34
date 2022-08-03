package ru.learnup.learnup.spring.mvc.homework34.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .headers().disable()
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .antMatchers("/user/**").hasRole("ROLE_USER")
                .antMatchers("/admin/**").hasRole("ROLE_ADMIN")
                .antMatchers("/orders").authenticated()
                .antMatchers("/books").authenticated()
                .antMatchers("/", "/orders/1").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
