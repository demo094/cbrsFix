package com.dataart.citybikerentalservicespring.configuration;

import com.dataart.citybikerentalservicespring.components.security.JwtAuthenticationProvider;
import com.dataart.citybikerentalservicespring.components.security.JwtTokenFilter;
import com.dataart.citybikerentalservicespring.components.security.RestUnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
    public JwtTokenFilter jwtTokenFilter() throws Exception {

        return new JwtTokenFilter();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint(entryPoint)
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/api/**").authenticated();
        http.authenticationProvider(jwtAuthenticationProvider);
        http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        http.headers().cacheControl();
    }
}






















