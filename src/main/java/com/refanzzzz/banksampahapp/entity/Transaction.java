package com.refanzzzz.banksampahapp.entity;

import com.refanzzzz.banksampahapp.constant.Constant;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = Constant.TRANSACTION_TABLE)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Customer customer;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;
}
