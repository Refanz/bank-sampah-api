package com.refanzzzz.banksampahapp.service;

import com.refanzzzz.banksampahapp.dto.request.auth.AuthRequest;
import com.refanzzzz.banksampahapp.dto.response.auth.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest request);
}

