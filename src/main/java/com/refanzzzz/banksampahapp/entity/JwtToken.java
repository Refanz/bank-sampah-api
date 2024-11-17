package com.refanzzzz.banksampahapp.entity;

import com.refanzzzz.banksampahapp.constant.Constant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = Constant.JWT_TOKEN_TABLE)
public class JwtToken {

    @Id
    private String id;

    @Column(name = "token")
    private String token;
}
