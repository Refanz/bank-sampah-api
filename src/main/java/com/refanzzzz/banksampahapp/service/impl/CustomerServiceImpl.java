package com.refanzzzz.banksampahapp.service.impl;

import com.refanzzzz.banksampahapp.dto.request.PagingRequest;
import com.refanzzzz.banksampahapp.dto.request.customer.CustomerRequest;
import com.refanzzzz.banksampahapp.dto.request.customer.CustomerUpdateRequest;
import com.refanzzzz.banksampahapp.dto.response.customer.CustomerWithPagingResponse;
import com.refanzzzz.banksampahapp.entity.Customer;
import com.refanzzzz.banksampahapp.repository.CustomerRepository;
import com.refanzzzz.banksampahapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void saveCustomer(CustomerRequest request) {

    }

    @Override
    public CustomerWithPagingResponse getAllCustomer(PagingRequest pagingRequest) {
        return null;
    }

    @Override
    public void updateCustomer(CustomerUpdateRequest request) {

    }

    @Override
    public void deleteCustomerById(String id) {
        Customer customer = getCustomerById(id);

        if (customer != null) {
            customerRepository.deleteCustomerById(id);
        }
    }

    @Override
    public Customer getCustomerById(String id) {
        Customer customer = customerRepository.getCustomerById(id);
        if (customer == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer is not found!");
        return customer;
    }
}
