package com.refanzzzz.banksampahapp.security;

import com.refanzzzz.banksampahapp.entity.UserAccount;
import com.refanzzzz.banksampahapp.service.JwtService;
import com.refanzzzz.banksampahapp.service.UserAccountService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserAccountService userAccountService;

    @Override
    protected void doFilterInternal(
            @NonNull
            HttpServletRequest request,
            @NonNull
            HttpServletResponse response,
            @NonNull
            FilterChain filterChain) throws ServletException, IOException {

        try {
            String token = jwtService.extractTokenFromRequest(request);

            if (token != null) {
                String userId = jwtService.getUserId(token);
                UserAccount userAccount = userAccountService.getUserAccountById(userId);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userAccount,
                        null,
                        userAccount.getAuthorities()
                );

                authenticationToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            log.error("Authentication failed: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
