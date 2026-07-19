package com.inkbound.security;

public record CustomPrincipal(
        String username,
        String role
) {}