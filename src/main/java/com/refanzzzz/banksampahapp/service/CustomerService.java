package com.refanzzzz.banksampahapp.service;

import com.refanzzzz.banksampahapp.dto.request.PagingRequest;
import com.refanzzzz.banksampahapp.dto.request.customer.CustomerCreateRequest;
import com.refanzzzz.banksampahapp.dto.request.customer.CustomerUpdateRequest;
import com.refanzzzz.banksampahapp.dto.response.customer.CustomerResponse;
import com.refanzzzz.banksampahapp.dto.response.customer.CustomerWithPagingResponse;

public interface CustomerService {
    void createCustomer(CustomerCreateRequest request);

    CustomerWithPagingResponse getAllCustomer(PagingRequest pagingRequest);

    void updateCustomer(String id, CustomerUpdateRequest request);

    void deleteCustomerById(String id);

    CustomerResponse getCustomerById(String id);
}
