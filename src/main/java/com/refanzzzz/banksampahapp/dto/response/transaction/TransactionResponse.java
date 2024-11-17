package com.refanzzzz.banksampahapp.dto.response.transaction;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TransactionResponse {
    private String id;
    private String customerId;
    private String transactionDate;
}
