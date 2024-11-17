package com.refanzzzz.banksampahapp.dto.response.customer;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CustomerResponse {
    private String id;
    private String username;
    private String role;
    private String name;
    private String phoneNumber;
    private String address;
    private String gender;
}
