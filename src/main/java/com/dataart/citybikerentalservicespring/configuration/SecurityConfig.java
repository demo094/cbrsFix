package com.dataart.citybikerentalservicespring.configuration;

import com.dataart.citybikerentalservicespring.components.security.JwtAuthenticationProvider;
import com.dataart.citybikerentalservicespring.components.security.JwtTokenFilter;
import com.dataart.citybikerentalservicespring.components.security.RestUnauthorizedEntryPoint;
import com.dataart.citybikerentalservicespring.view.controller.mainpage.AppExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

/**
 * Created by mkrasowski on 10.10.2016.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestUnauthorizedEntryPoint entryPoint;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    @Autowired
    private JwtTokenFilter jwtTokenFilter;
    @Autowired
    private AppExceptionHandler appExceptionHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
//                .exceptionHandling().accessDeniedHandler(appExceptionHandler.handleAccessDeniedException())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/**").authenticated()
                .and()
                .authenticationProvider(jwtAuthenticationProvider)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .headers().cacheControl();
    }
}






















