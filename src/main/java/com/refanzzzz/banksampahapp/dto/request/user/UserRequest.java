package com.refanzzzz.banksampahapp.dto.request.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserRequest {
    private String username;
    private String password;
    private String role;
}
