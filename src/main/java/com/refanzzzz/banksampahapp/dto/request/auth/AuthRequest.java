package com.refanzzzz.banksampahapp.dto.request.auth;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AuthRequest {
    private String username;
    private String password;
}
