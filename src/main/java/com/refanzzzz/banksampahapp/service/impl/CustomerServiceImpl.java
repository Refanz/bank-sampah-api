package com.refanzzzz.banksampahapp.service.impl;

import com.refanzzzz.banksampahapp.constant.Gender;
import com.refanzzzz.banksampahapp.dto.request.PagingRequest;
import com.refanzzzz.banksampahapp.dto.request.customer.CustomerRequest;
import com.refanzzzz.banksampahapp.dto.request.customer.CustomerUpdateRequest;
import com.refanzzzz.banksampahapp.dto.request.user.UserRequest;
import com.refanzzzz.banksampahapp.dto.response.customer.CustomerWithPagingResponse;
import com.refanzzzz.banksampahapp.entity.Customer;
import com.refanzzzz.banksampahapp.entity.UserAccount;
import com.refanzzzz.banksampahapp.repository.CustomerRepository;
import com.refanzzzz.banksampahapp.service.CustomerService;
import com.refanzzzz.banksampahapp.service.UserAccountService;
import com.refanzzzz.banksampahapp.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final UserAccountService userAccountService;
    private final CustomerRepository customerRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createCustomer(CustomerRequest request) {
        UserRequest userRequest = UserRequest.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .role(request.getRole())
                .build();

        UserAccount userAccount = userAccountService.createUserAccount(userRequest);

        Gender gender = Gender.getGenderByName(request.getGender());

        if (gender == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gender is not found");

        customerRepository.saveCustomer(
                Util.generateUUID(),
                request.getName(),
                request.getPhoneNumber(),
                request.getAddress(),
                gender.name(),
                userAccount.getId()
        );
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
