package com.refanzzzz.banksampahapp.dto.response.transaction;

import com.refanzzzz.banksampahapp.dto.response.PagingResponse;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TransactionWithPagingResponse {
    List<TransactionDetailResponse> transactionDetailResponse;
    PagingResponse pagingResponse;
}
