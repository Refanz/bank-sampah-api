package com.refanzzzz.banksampahapp.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PagingResponse {
    private Integer totalItems;
    private Integer totalPages;
    private Integer page;
    private Integer size;
}
