package com.example.demoDatabase.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailService;

    public JwtAuthenticationFilter(JwtUtils jwtUtils, UserDetailsService userDetailService) {
        this.jwtUtils = jwtUtils;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String token = jwtUtils.getToken(request);
//
//        if (jwtUtils.validateToken(token)) {
//            UserDetailsImpl user = (UserDetailsImpl) userDetailService.loadUserByUsername(jwtUtils.getUsername(token));
//            SecurityContextHolder.getContext()
//                    .setAuthentication(
//                            new UsernamePasswordAuthenticationToken(
//                                    user.getUsername(),
//                                    token,
//                                    user.getAuthorities()
//                            )
//                    );
//        }
        filterChain.doFilter(request, response);
    }
}
