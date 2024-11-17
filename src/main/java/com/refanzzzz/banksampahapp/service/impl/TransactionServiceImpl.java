package com.refanzzzz.banksampahapp.service.impl;

import com.refanzzzz.banksampahapp.dto.request.PagingRequest;
import com.refanzzzz.banksampahapp.dto.request.transaction.TransactionDetailRequest;
import com.refanzzzz.banksampahapp.dto.request.transaction.TransactionRequest;
import com.refanzzzz.banksampahapp.dto.response.PagingResponse;
import com.refanzzzz.banksampahapp.dto.response.customer.CustomerResponse;
import com.refanzzzz.banksampahapp.dto.response.transaction.TransactionDetailResponse;
import com.refanzzzz.banksampahapp.dto.response.transaction.TransactionResponse;
import com.refanzzzz.banksampahapp.dto.response.transaction.TransactionWithPagingResponse;
import com.refanzzzz.banksampahapp.dto.response.trash.TrashResponse;
import com.refanzzzz.banksampahapp.repository.TransactionRepository;
import com.refanzzzz.banksampahapp.service.CustomerService;
import com.refanzzzz.banksampahapp.service.TransactionService;
import com.refanzzzz.banksampahapp.service.TrashService;
import com.refanzzzz.banksampahapp.util.DateUtil;
import com.refanzzzz.banksampahapp.util.PagingUtil;
import com.refanzzzz.banksampahapp.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CustomerService customerService;
    private final TrashService trashService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createTransaction(TransactionRequest request) {
        CustomerResponse customer = customerService.getCustomerById(request.getCustomerId());
        transactionRepository.createTransaction(Util.generateUUID(), customer.getId(), DateUtil.getCurrentDate());
    }

    @Transactional(readOnly = true)
    @Override
    public TransactionResponse getTransactionById(String transactionId) {
        List<Object[]> transactionObject = transactionRepository.getTransactionById(transactionId);

        Optional<TransactionResponse> transactionResponse = transactionObject.stream().map((o) -> TransactionResponse.builder()
                .id(o[0].toString())
                .customerId(o[1].toString())
                .transactionDate(o[2].toString())
                .build()).findFirst();

        return transactionResponse.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction is not found"));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addTransactionDetail(String transactionId, TransactionDetailRequest request) {
        TransactionResponse transaction = getTransactionById(transactionId);
        TrashResponse trash = trashService.getTrashById(request.getTrashId());

        transactionRepository.addTransactionDetail(
                Util.generateUUID(),
                trash.getPrice(),
                request.getTotalTrashWeight(),
                transaction.getId(),
                trash.getId());
    }

    @Transactional(readOnly = true)
    public TransactionWithPagingResponse getAllTransactionWithPagination(PagingRequest request) {
        int totalItems = transactionRepository.getTransactionCount();
        int offset = PagingUtil.generateOffset(request.getLimit(), request.getPage());
        int totalPages = PagingUtil.getTotalPage(totalItems, request.getLimit());

        PagingResponse pagingResponse = PagingResponse.builder()
                .page(request.getPage())
                .totalPages(totalPages)
                .size(request.getLimit())
                .totalItems(totalItems)
                .build();

        List<Object[]> transactionObject = transactionRepository.getAllTransaction(request.getLimit(), offset);

        List<TransactionDetailResponse> transactionResponseList = transactionObject.stream().map((data) -> TransactionDetailResponse.builder()
                .id((String) data[0])
                .transactionDate(data[1].toString())
                .customerName((String) data[2])
                .trashPrice(data[3] != null ? (Long) data[3] : 0)
                .totalTrashWeight(data[4] != null ? (int) data[4] : 0)
                .trashName((String) data[5])
                .build()).toList();

        return mapToTransactionPagingResponse(transactionResponseList, pagingResponse);
    }

    private TransactionWithPagingResponse mapToTransactionPagingResponse(List<TransactionDetailResponse> transactionResponseList, PagingResponse paging) {
        return TransactionWithPagingResponse.builder()
                .pagingResponse(paging)
                .transactionDetailResponse(transactionResponseList)
                .build();
    }
}
