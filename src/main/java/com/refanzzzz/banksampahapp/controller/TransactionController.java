package com.refanzzzz.banksampahapp.controller;

import com.refanzzzz.banksampahapp.constant.Constant;
import com.refanzzzz.banksampahapp.dto.request.PagingRequest;
import com.refanzzzz.banksampahapp.dto.request.transaction.TransactionDetailRequest;
import com.refanzzzz.banksampahapp.dto.request.transaction.TransactionRequest;
import com.refanzzzz.banksampahapp.dto.response.transaction.TransactionResponse;
import com.refanzzzz.banksampahapp.dto.response.transaction.TransactionWithPagingResponse;
import com.refanzzzz.banksampahapp.service.TransactionService;
import com.refanzzzz.banksampahapp.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Constant.TRANSACTION_API)
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody TransactionRequest request) {
        transactionService.createTransaction(request);
        return ResponseUtil.createResponse(HttpStatus.CREATED, "Successfully create transaction", null);
    }

    @PostMapping("/{id}/transaction_details")
    public ResponseEntity<?> addTransactionDetail(@PathVariable String id, @RequestBody TransactionDetailRequest request) {
        transactionService.addTransactionDetail(id, request);
        return ResponseUtil.createResponse(HttpStatus.CREATED, "Successfully add transaction detail", null);
    }

    @GetMapping
    public ResponseEntity<?> getAllTransaction(
            @RequestParam(defaultValue = "10", required = false) int size,
            @RequestParam(defaultValue = "1", required = false) int page
    ) {
        PagingRequest pagingRequest = PagingRequest.builder()
                .limit(size)
                .page(page)
                .build();

        TransactionWithPagingResponse transactionResponse = transactionService.getAllTransactionWithPagination(pagingRequest);
        return ResponseUtil.createResponseWithPaging(HttpStatus.OK, "Successfully get all transaction", transactionResponse.getTransactionDetailResponse(), transactionResponse.getPagingResponse());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable String id) {
        TransactionResponse transaction = transactionService.getTransactionById(id);
        return ResponseUtil.createResponse(HttpStatus.OK, "Successfully get transaction by id", transaction);
    }
}