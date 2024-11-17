package com.refanzzzz.banksampahapp.dto.request.customer;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CustomerRequest {
    private String username;
    private String password;
    private String role;
    private String name;
    private String phoneNumber;
    private String address;
    private String gender;
}
