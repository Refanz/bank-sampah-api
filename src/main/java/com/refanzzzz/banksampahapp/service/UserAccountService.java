package com.refanzzzz.banksampahapp.service;

import com.refanzzzz.banksampahapp.dto.request.user.UserRequest;
import com.refanzzzz.banksampahapp.entity.UserAccount;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserAccountService extends UserDetailsService {
    UserAccount createUserAccount(UserRequest userRequest);

    UserAccount getUserAccountById(String id);

    UserAccount getUserAccountByUsername(String username);
}
