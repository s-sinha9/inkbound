package com.inkbound.security;

import java.util.UUID;

public record CustomPrincipal(
        String username,
        String role,
        UUID userId
) {}