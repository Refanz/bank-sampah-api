package com.refanzzzz.banksampahapp.entity;

import com.refanzzzz.banksampahapp.constant.Constant;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Entity
@Table(name = Constant.TRANSACTION_DETAIL_TABLE)
public class TransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "price")
    private Long price;

    @Column(name = "total_trash_weight")
    private Integer totalTrashWeight;

    @ManyToOne
    private Transaction transaction;

    @ManyToOne
    private Trash trash;
}
