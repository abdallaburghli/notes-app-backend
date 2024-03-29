package com.example.notesApp.security;

import com.example.notesApp.config.CurrentPrincipal;
import com.example.notesApp.service.AuthenticationService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class AuthFilter extends BasicAuthenticationFilter {

    private final AuthenticationService authenticationService;

    public AuthFilter(AuthenticationManager authenticationManager, AuthenticationService authenticationService) {
        super(authenticationManager);
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);

            CurrentPrincipal principal = authenticationService.authenticate(token);

            var authentication = new UsernamePasswordAuthenticationToken(principal, null, new ArrayList<>());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
        } catch (Exception e) {
            throw new RuntimeException("Error Authentication");
        }
    }
}
