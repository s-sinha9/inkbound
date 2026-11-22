package com.inkbound.config;

import com.inkbound.security.CustomPrincipal;
import com.inkbound.security.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log =
            LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.warn("Authorization header missing or invalid format");
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7);
        try {
            String username = jwtService.extractUsername(jwt);

            if (username == null) {
                log.warn("Username could not be extracted from JWT");
                filterChain.doFilter(request, response);
                return;
            }

            if (SecurityContextHolder.getContext().getAuthentication() != null) {
                log.warn("SecurityContext already contains authentication");
                filterChain.doFilter(request, response);
                return;
            }

            String role = jwtService.extractRole(jwt);
            UUID userId = jwtService.extractUserId(jwt);
            List<GrantedAuthority> authorities =
                    List.of(new SimpleGrantedAuthority(role));
            CustomPrincipal principal =
                    new CustomPrincipal(username, role, userId);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            principal,
                            null,
                            authorities
                    );

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

            } catch (Exception e) {
            log.error("JWT authentication failed: {}",
                    e.getMessage(), e);

            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
        log.info("Response status: {}", response.getStatus());
    }
}