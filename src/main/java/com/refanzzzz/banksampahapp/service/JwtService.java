package com.refanzzzz.banksampahapp.service;

import com.refanzzzz.banksampahapp.entity.UserAccount;
import jakarta.servlet.http.HttpServletRequest;

public interface JwtService {
    String generateAccessToken(UserAccount userAccount);

    String getUserId(String token);

    String extractTokenFromRequest(HttpServletRequest httpServletRequest);
}
