package com.refanzzzz.banksampahapp.controller;

import com.refanzzzz.banksampahapp.constant.Constant;
import com.refanzzzz.banksampahapp.dto.request.PagingRequest;
import com.refanzzzz.banksampahapp.dto.request.customer.CustomerCreateRequest;
import com.refanzzzz.banksampahapp.dto.request.customer.CustomerUpdateRequest;
import com.refanzzzz.banksampahapp.dto.response.customer.CustomerResponse;
import com.refanzzzz.banksampahapp.dto.response.customer.CustomerWithPagingResponse;
import com.refanzzzz.banksampahapp.service.CustomerService;
import com.refanzzzz.banksampahapp.util.ResponseUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Constant.CUSTOMER_API)
@RequiredArgsConstructor
@Tag(name = "Customer Management", description = "API to manage Customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getAllCustomer(
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(defaultValue = "1", required = false) int page
    ) {
        PagingRequest pagingRequest = PagingRequest.builder()
                .page(page)
                .limit(size)
                .build();

        CustomerWithPagingResponse customerWithPagingResponse = customerService.getAllCustomer(pagingRequest);
        return ResponseUtil.createResponseWithPaging(HttpStatus.OK, "Successfully get all customers", customerWithPagingResponse.getCustomers(), customerWithPagingResponse.getPaging());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable String id) {
        CustomerResponse customer = customerService.getCustomerById(id);
        return ResponseUtil.createResponse(HttpStatus.OK, "Successfully get customer by id", customer);
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody CustomerCreateRequest customerCreateRequest) {
        customerService.createCustomer(customerCreateRequest);
        return ResponseUtil.createResponse(HttpStatus.CREATED, "Successfully create customer", null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable String id, @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        customerService.updateCustomer(id, customerUpdateRequest);
        return ResponseUtil.createResponse(HttpStatus.OK, "Successfully update customer", null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable String id) {
        customerService.deleteCustomerById(id);
        return ResponseUtil.createResponse(HttpStatus.OK, "Successfully delete customer by id", null);
    }
}
