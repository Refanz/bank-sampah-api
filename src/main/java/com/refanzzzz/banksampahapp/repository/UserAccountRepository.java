package com.refanzzzz.banksampahapp.repository;

import com.refanzzzz.banksampahapp.constant.query.UserAccountQuery;
import com.refanzzzz.banksampahapp.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

    @Modifying
    @Query(nativeQuery = true, value = UserAccountQuery.INSERT_USER)
    void createUserAccount(String id, String username, String password, String role);

    @Query(nativeQuery = true, value = UserAccountQuery.GET_USER_ACCOUNT_BY_ID)
    List<Object[]> getUserAccountById(String id);

    @Query(nativeQuery = true, value = UserAccountQuery.GET_USER_ACCOUNT_BY_USERNAME)
    List<Object[]> getUserAccountByUsername(String username);
}
