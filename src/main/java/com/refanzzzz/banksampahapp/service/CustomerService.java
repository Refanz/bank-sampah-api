package com.refanzzzz.banksampahapp.service;

import com.refanzzzz.banksampahapp.dto.request.PagingRequest;
import com.refanzzzz.banksampahapp.dto.request.customer.CustomerRequest;
import com.refanzzzz.banksampahapp.dto.request.customer.CustomerUpdateRequest;
import com.refanzzzz.banksampahapp.dto.response.customer.CustomerWithPagingResponse;
import com.refanzzzz.banksampahapp.entity.Customer;

public interface CustomerService {
    void saveCustomer(CustomerRequest request);

    CustomerWithPagingResponse getAllCustomer(PagingRequest pagingRequest);

    void updateCustomer(CustomerUpdateRequest request);

    void deleteCustomerById(String id);

    Customer getCustomerById(String id);
}
