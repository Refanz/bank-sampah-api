package com.refanzzzz.banksampahapp.dto.response.trash;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TrashResponse {
    private String id;
    private String name;
    private String unit;
    private Long price;
}
