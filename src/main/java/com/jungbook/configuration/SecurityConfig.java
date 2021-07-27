package com.jungbook.configuration;

import com.jungbook.handler.MyLoginSuccessHandler;
import com.jungbook.service.BackLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private BackLoginService backLoginService;

    @Autowired
    public SecurityConfig(com.jungbook.service.BackLoginService backLoginService) {
        this.backLoginService = backLoginService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers( "/service","/resources/**","/user/new","/user/duplicate").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/user")
                .usernameParameter("id")
                .passwordParameter("pw")
                .loginProcessingUrl("/user")
                .successHandler(new MyLoginSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(backLoginService)
                .and()
                .inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("pw")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("pw")).roles("ADMIN");
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}