package com.refanzzzz.banksampahapp.dto.response.customer;

import com.refanzzzz.banksampahapp.dto.response.PagingResponse;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CustomerWithPagingResponse {
    List<CustomerResponse> customers;
    PagingResponse paging;
}
