package com.refanzzzz.banksampahapp.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PagingRequest {
    private Integer limit;
    private Integer page;
}
