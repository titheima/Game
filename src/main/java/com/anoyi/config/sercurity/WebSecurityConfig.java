package com.anoyi.config.sercurity;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置
 */
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AnyUserDetailsService anyUserDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.anyUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/app/**").authenticated()
                .anyRequest().permitAll()
                .and().formLogin().loginPage("/login").permitAll()
                .and().rememberMe().alwaysRemember(true)
                .and().logout().permitAll()
                .and().csrf().disable();
    }

}