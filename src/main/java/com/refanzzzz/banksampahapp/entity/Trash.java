package com.refanzzzz.banksampahapp.entity;

import com.refanzzzz.banksampahapp.constant.Constant;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = Constant.TRASH_TABLE)
public class Trash extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "unit")
    private String unit;

    @Column(name = "price")
    private Long price;
}
