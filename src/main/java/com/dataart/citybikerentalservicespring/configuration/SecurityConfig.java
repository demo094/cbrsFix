package com.dataart.citybikerentalservicespring.configuration;

import com.dataart.citybikerentalservicespring.components.security.JwtAuthenticationProvider;
import com.dataart.citybikerentalservicespring.components.security.JwtTokenFilter;
import com.dataart.citybikerentalservicespring.components.security.RestUnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

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

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return new ProviderManager(Collections.singletonList(jwtAuthenticationProvider));
    }

//    @Bean
//    public JwtAuthenticationFilter authenticationFilter() throws Exception{
//        JwtAuthenticationFilter authenticationFilter = new JwtAuthenticationFilter();
//        authenticationFilter.setAuthenticationManager(authenticationManager());
//        authenticationFilter.setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler());
//        authenticationFilter.setAuthenticationFailureHandler(new JwtAuthenticationFailureHandler());
//        return authenticationFilter;
//    }

    @Bean
    public JwtTokenFilter jwtTokenFilter() throws Exception {
        return new JwtTokenFilter();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .and().exceptionHandling().authenticationEntryPoint(entryPoint)
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests().antMatchers(
                HttpMethod.GET,
                "/",
                "/*.html",
                "/*.jsp",
                "/*.png",
                "/favicon.ico",
                "/**/*.html",
                "/**/*.css",
                "/**/*.js").permitAll()

//                .antMatchers("/api/**").authenticated();
                .anyRequest().authenticated();
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        http.headers().cacheControl();
    }
}






















