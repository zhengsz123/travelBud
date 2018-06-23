package com.travel.core.config;

import com.travel.core.extend.security.MyAuthenticaitonEntrypoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyAuthenticaitonEntrypoint myAuthenticaitonEntrypoint;
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("zhengshi")
                .password("{noop}password123").roles("REGISTERED_USER");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/api/users/login").permitAll()
                .and()
                    .authorizeRequests().antMatchers("/api/**").hasAnyRole("REGISTERED_USER")
            .and()
                    .exceptionHandling().authenticationEntryPoint(myAuthenticaitonEntrypoint);

    }


}
