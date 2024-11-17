package com.refanzzzz.banksampahapp.dto.response.transaction;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class TransactionDetailResponse {
    private String id;
    private String transactionDate;
    private String customerName;
    private Long trashPrice;
    private Integer totalTrashWeight;
    private String trashName;
}
