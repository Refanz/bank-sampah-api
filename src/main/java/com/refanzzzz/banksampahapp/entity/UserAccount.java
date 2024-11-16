package com.refanzzzz.banksampahapp.entity;

import com.refanzzzz.banksampahapp.constant.Constant;
import com.refanzzzz.banksampahapp.constant.UserRole;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = Constant.USER_ACCOUNT_TABLE)
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
