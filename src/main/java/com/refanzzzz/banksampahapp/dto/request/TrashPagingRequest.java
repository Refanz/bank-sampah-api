package com.refanzzzz.banksampahapp.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TrashPagingRequest {
    private Integer limit;
    private Integer page;
}
