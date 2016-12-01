package com.dataart.citybikerentalservicespring.components.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by mkrasowski on 28.11.2016.
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getCookies() != null) {
            Optional<String> tokenOptional = Stream.of(request.getCookies())
                    .filter(cookie -> cookie.getName().equals("accessToken"))
                    .map(Cookie::getValue)
                    .filter(token-> !token.isEmpty())
                    .findAny();

            tokenOptional.ifPresent(token -> {
                    JwtAuthenticationToken authenticationToken = new JwtAuthenticationToken(token);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            });
        }

        filterChain.doFilter(request, response);
    }
}
