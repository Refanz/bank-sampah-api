package com.refanzzzz.banksampahapp.dto.request.transaction;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class TransactionDetailRequest {
    private Integer totalTrashWeight;
    private String trashId;
}
