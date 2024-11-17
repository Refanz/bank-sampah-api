package com.refanzzzz.banksampahapp.dto.response.trash;

import com.refanzzzz.banksampahapp.dto.response.PagingResponse;
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
