package com.refanzzzz.banksampahapp.repository;

import com.refanzzzz.banksampahapp.constant.query.CustomerQuery;
import com.refanzzzz.banksampahapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query(nativeQuery = true, value = CustomerQuery.GET_ALL_CUSTOMER_WITH_PAGINATION)
    List<Customer> getAllCustomer(int limit, int offset);

    @Query(nativeQuery = true, value = CustomerQuery.GET_CUSTOMER_BY_ID)
    Customer getCustomerById(String id);

    @Query(nativeQuery = true, value = CustomerQuery.GET_CUSTOMER_COUNT)
    int getCustomerCount();

    @Modifying
    @Query(nativeQuery = true, value = CustomerQuery.INSERT_CUSTOMER)
    void saveCustomer(String id, String name, String phoneNumber, String address, String gender, String userAccountId);

    @Modifying
    @Query(nativeQuery = true, value = CustomerQuery.UPDATE_CUSTOMER)
    void updateCustomer(String name, String phoneNumber, String address, String gender, String id);

    @Modifying
    @Query(nativeQuery = true, value = CustomerQuery.DELETE_CUSTOMER_BY_ID)
    void deleteCustomerById(String id);
}
