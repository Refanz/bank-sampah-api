package com.refanzzzz.banksampahapp.dto.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TrashWithPagingResponse {
    List<TrashResponse> trashes;
    PagingResponse paging;
}
