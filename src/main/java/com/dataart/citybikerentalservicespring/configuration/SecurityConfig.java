package com.dataart.citybikerentalservicespring.configuration;

import com.dataart.citybikerentalservicespring.components.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

import static org.hibernate.criterion.Restrictions.and;

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
    private JsonWebTokenAuthenticationProvider jsonWebTokenAuthenticationProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(Collections.singletonList(jsonWebTokenAuthenticationProvider));
    }

    @Bean
    public JsonWebTokenAuthenticationFilter authenticationFilter() throws Exception{
        JsonWebTokenAuthenticationFilter authenticationFilter = new JsonWebTokenAuthenticationFilter();
        authenticationFilter.setAuthenticationManager(authenticationManager());
        authenticationFilter.setAuthenticationSuccessHandler(new JsonWebTokenAuthenticationSuccessHandler());
        authenticationFilter.setAuthenticationFailureHandler(new JsonWebTokenAuthenticationFailureHandler());
        return authenticationFilter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .and().exceptionHandling().authenticationEntryPoint(entryPoint).accessDeniedPage("/accessdenied")
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/**").authenticated();
        http.headers().cacheControl();
    }
}






















