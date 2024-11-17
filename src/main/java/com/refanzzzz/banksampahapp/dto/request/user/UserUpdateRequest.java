package com.refanzzzz.banksampahapp.dto.request.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserUpdateRequest {
    private String currentPassword;
    private String newPassword;
}
