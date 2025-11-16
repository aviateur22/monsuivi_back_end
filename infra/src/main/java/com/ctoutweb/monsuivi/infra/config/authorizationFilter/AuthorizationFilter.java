package com.ctoutweb.monsuivi.infra.config.authorizationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {
    private final AuthorizationManager authorizationManager;

    public AuthorizationFilter(AuthorizationManager authorizationManager) {
        this.authorizationManager = authorizationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getRequestURI().contains("auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        authorizationManager.manageRequestAuthorization(request);
        filterChain.doFilter(request, response);
    }
}
