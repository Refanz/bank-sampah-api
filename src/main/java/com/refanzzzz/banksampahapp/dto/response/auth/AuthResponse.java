package com.refanzzzz.banksampahapp.dto.response.auth;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AuthResponse {
    private String accessToken;
    private String role;
}
