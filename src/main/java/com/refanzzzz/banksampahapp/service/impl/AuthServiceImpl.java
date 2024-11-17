package com.refanzzzz.banksampahapp.service.impl;

import com.refanzzzz.banksampahapp.dto.request.auth.AuthRequest;
import com.refanzzzz.banksampahapp.dto.response.auth.AuthResponse;
import com.refanzzzz.banksampahapp.entity.UserAccount;
import com.refanzzzz.banksampahapp.service.AuthService;
import com.refanzzzz.banksampahapp.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(AuthRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserAccount userAccount = (UserAccount) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(userAccount);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .role(userAccount.getRole().getName())
                .build();
    }
}
