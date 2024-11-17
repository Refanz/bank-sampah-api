package com.refanzzzz.banksampahapp.dto.request.trash;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TrashRequest {
    private String name;
    private String unit;
    private Long price;
}
