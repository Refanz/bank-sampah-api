package com.refanzzzz.banksampahapp.service.impl;

import com.refanzzzz.banksampahapp.constant.UserRole;
import com.refanzzzz.banksampahapp.dto.request.user.UserRequest;
import com.refanzzzz.banksampahapp.entity.UserAccount;
import com.refanzzzz.banksampahapp.repository.UserAccountRepository;
import com.refanzzzz.banksampahapp.service.UserAccountService;
import com.refanzzzz.banksampahapp.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final PasswordEncoder passwordEncoder;
    private final UserAccountRepository userAccountRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserAccount createUserAccount(UserRequest userRequest) {
        String userAccountId = Util.generateUUID();

        UserRole userRole = UserRole.getUserRoleByName(userRequest.getRole());

        if (userRole == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User role: " + userRequest.getRole() + " is not found!");

        userAccountRepository.createUserAccount(
                userAccountId,
                userRequest.getUsername(),
                passwordEncoder.encode(userRequest.getPassword()),
                userRole.name());

        return getUserAccountById(userAccountId);
    }

    @Override
    public UserAccount getUserAccountById(String id) {
        List<Object[]> userAccountObject = userAccountRepository.getUserAccountById(id);

        if (userAccountObject.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User account is not found");

        Optional<UserAccount> account = userAccountObject.stream().map(o -> mapToUserAccount(
                        o[0].toString(),
                        o[1].toString(),
                        o[2].toString(),
                        o[3].toString()))
                .findFirst();

        return account.get();
    }

    @Override
    public UserAccount getUserAccountByUsername(String username) {
        List<Object[]> userAccountObject = userAccountRepository.getUserAccountByUsername(username);

        if (userAccountObject.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User account is not found!");

        Optional<UserAccount> userAccount = userAccountObject.stream().map(o -> mapToUserAccount(
                o[0].toString(),
                o[1].toString(),
                o[2].toString(),
                o[3].toString()
        )).findFirst();

        return userAccount.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserAccountByUsername(username);
    }

    private UserAccount mapToUserAccount(String id, String username, String password, String role) {
        return UserAccount.builder()
                .username(username)
                .id(id)
                .password(password)
                .role(UserRole.getUserRoleByName(role))
                .build();
    }
}
