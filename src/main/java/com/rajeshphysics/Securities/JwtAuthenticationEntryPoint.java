package com.rajeshphysics.Securities;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        // Log the unauthorized access attempt
        logger.warn("Unauthorized request - {} {} - {}", request.getMethod(), request.getRequestURI(), authException.getMessage());

        // Set response status and content type
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        // Create a detailed error response
        String jsonResponse = String.format("{\"error\": \"Unauthorized\", \"message\": \"%s\", \"path\": \"%s\"}",
                                             authException.getMessage(), request.getRequestURI());

        // Write the error response to the client
        response.getWriter().write(jsonResponse);
    }
}
