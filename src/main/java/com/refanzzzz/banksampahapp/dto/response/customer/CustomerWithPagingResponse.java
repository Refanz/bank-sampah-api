package com.refanzzzz.banksampahapp.dto.response.customer;

import com.refanzzzz.banksampahapp.dto.response.PagingResponse;

import java.util.List;

public class CustomerWithPagingResponse {
    List<CustomerResponse> customers;
    PagingResponse paging;
}
