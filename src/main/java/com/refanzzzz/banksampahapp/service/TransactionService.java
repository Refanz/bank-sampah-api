package com.refanzzzz.banksampahapp.service;

import com.refanzzzz.banksampahapp.dto.request.PagingRequest;
import com.refanzzzz.banksampahapp.dto.request.transaction.TransactionDetailRequest;
import com.refanzzzz.banksampahapp.dto.request.transaction.TransactionRequest;
import com.refanzzzz.banksampahapp.dto.response.transaction.TransactionResponse;
import com.refanzzzz.banksampahapp.dto.response.transaction.TransactionWithPagingResponse;

public interface TransactionService {
    void createTransaction(TransactionRequest request);

    TransactionResponse getTransactionById(String transactionId);

    void addTransactionDetail(String transactionId, TransactionDetailRequest request);

    TransactionWithPagingResponse getAllTransactionWithPagination(PagingRequest request);
}
