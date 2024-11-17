package com.refanzzzz.banksampahapp.service.impl;

import com.refanzzzz.banksampahapp.constant.Gender;
import com.refanzzzz.banksampahapp.constant.UserRole;
import com.refanzzzz.banksampahapp.dto.request.PagingRequest;
import com.refanzzzz.banksampahapp.dto.request.customer.CustomerCreateRequest;
import com.refanzzzz.banksampahapp.dto.request.customer.CustomerUpdateRequest;
import com.refanzzzz.banksampahapp.dto.request.user.UserRequest;
import com.refanzzzz.banksampahapp.dto.response.PagingResponse;
import com.refanzzzz.banksampahapp.dto.response.customer.CustomerResponse;
import com.refanzzzz.banksampahapp.dto.response.customer.CustomerWithPagingResponse;
import com.refanzzzz.banksampahapp.entity.UserAccount;
import com.refanzzzz.banksampahapp.repository.CustomerRepository;
import com.refanzzzz.banksampahapp.service.CustomerService;
import com.refanzzzz.banksampahapp.service.UserAccountService;
import com.refanzzzz.banksampahapp.util.PagingUtil;
import com.refanzzzz.banksampahapp.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final UserAccountService userAccountService;
    private final CustomerRepository customerRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createCustomer(CustomerCreateRequest request) {
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

    @Transactional(readOnly = true)
    @Override
    public CustomerWithPagingResponse getAllCustomer(PagingRequest pagingRequest) {
        int totalItems = customerRepository.getCustomerCount();
        int offset = PagingUtil.generateOffset(pagingRequest.getLimit(), pagingRequest.getPage());
        int totalPages = PagingUtil.getTotalPage(totalItems, pagingRequest.getLimit());

        PagingResponse pagingResponse = PagingResponse.builder()
                .page(pagingRequest.getPage())
                .size(pagingRequest.getLimit())
                .totalItems(totalItems)
                .totalPages(totalPages)
                .build();

        List<Object[]> customerObject = customerRepository.getAllCustomer(pagingRequest.getLimit(), offset);

        List<CustomerResponse> customerResponseList = customerObject.stream().map((data) -> CustomerResponse.builder()
                .id(data[0].toString())
                .name(data[1].toString())
                .phoneNumber(data[2].toString())
                .gender(Gender.getNameByGender(data[3].toString()))
                .address(data[4].toString())
                .username(data[5].toString())
                .role(UserRole.getNameByUserRole(data[6].toString()))
                .build()).toList();

        return mapToCustomerWithPagingResponse(customerResponseList, pagingResponse);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCustomer(String id, CustomerUpdateRequest request) {
        CustomerResponse customer = getCustomerById(id);

        Gender gender = Gender.getGenderByName(request.getGender());

        if (gender == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Gender is not found");

        if (customer != null) {
            customerRepository.updateCustomer(
                    request.getName(),
                    request.getPhoneNumber(),
                    request.getAddress(),
                    gender.name(),
                    id);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteCustomerById(String id) {
        CustomerResponse customer = getCustomerById(id);

        if (customer != null) {
            customerRepository.deleteCustomerById(id);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerResponse getCustomerById(String id) {
        List<Object[]> customerObject = customerRepository.getCustomerById(id);

        if (customerObject.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer is not found");

        Optional<CustomerResponse> customerResponse = customerObject.stream().map((o) -> CustomerResponse.builder()
                .id(o[0].toString())
                .name(o[1].toString())
                .phoneNumber(o[2].toString())
                .gender(Gender.getNameByGender(o[3].toString()))
                .address(o[4].toString())
                .username(o[5].toString())
                .role(UserRole.getNameByUserRole(o[6].toString()))
                .build()).findFirst();

        return customerResponse.get();
    }

    private CustomerWithPagingResponse mapToCustomerWithPagingResponse(List<CustomerResponse> customerResponseList, PagingResponse pagingResponse) {
        return CustomerWithPagingResponse.builder()
                .customers(customerResponseList)
                .paging(pagingResponse)
                .build();
    }
}
