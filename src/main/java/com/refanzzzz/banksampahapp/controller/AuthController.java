package com.refanzzzz.banksampahapp.controller;

import com.refanzzzz.banksampahapp.constant.Constant;
import com.refanzzzz.banksampahapp.dto.request.auth.AuthRequest;
import com.refanzzzz.banksampahapp.dto.response.auth.AuthResponse;
import com.refanzzzz.banksampahapp.service.AuthService;
import com.refanzzzz.banksampahapp.util.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constant.AUTH_API)
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "APIs for Authentication")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        AuthResponse authResponse = authService.login(request);
        return ResponseUtil.createResponse(HttpStatus.OK, "Login success", authResponse);
    }
}
