package com.refanzzzz.banksampahapp.repository;

import com.refanzzzz.banksampahapp.constant.query.TransactionQuery;
import com.refanzzzz.banksampahapp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    @Modifying
    @Query(nativeQuery = true, value = TransactionQuery.CREATE_TRANSACTION)
    void createTransaction(String id, String customerId, LocalDateTime transactionDate);

    @Modifying
    @Query(nativeQuery = true, value = TransactionQuery.CREATE_TRANSACTION_DETAIL)
    void addTransactionDetail(String id, long price, int totalTrashWeight, String transactionId, String trashId);

    @Query(nativeQuery = true, value = TransactionQuery.GET_ALL_TRANSACTION_WITH_PAGINATION)
    List<Object[]> getAllTransaction(int limit, int offset);

    @Query(nativeQuery = true, value = TransactionQuery.GET_TRANSACTION_COUNT)
    int getTransactionCount();

    @Query(nativeQuery = true, value = TransactionQuery.GET_TRANSACTION_BY_ID)
    List<Object[]> getTransactionById(String id);
}
