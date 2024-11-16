package com.refanzzzz.banksampahapp.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ApiResponse<T> {
    private Integer status;
    private String message;
    private T data;
    private PagingResponse paging;
}
