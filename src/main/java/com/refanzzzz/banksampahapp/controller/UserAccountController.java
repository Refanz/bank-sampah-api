package com.refanzzzz.banksampahapp.controller;

import com.refanzzzz.banksampahapp.constant.Constant;
import com.refanzzzz.banksampahapp.dto.request.user.UserRequest;
import com.refanzzzz.banksampahapp.dto.request.user.UserUpdateRequest;
import com.refanzzzz.banksampahapp.service.UserAccountService;
import com.refanzzzz.banksampahapp.util.ResponseUtil;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "User Account Management", description = "API to Manage User Account")
@RequestMapping(Constant.USER_ACCOUNT_API)
@SecurityRequirement(name = "Bearer Authentication")
public class UserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping
    public ResponseEntity<?> createUserAccount(@RequestBody UserRequest userRequest) {
        userAccountService.createUserAccount(userRequest);
        return ResponseUtil.createResponse(HttpStatus.CREATED, "Successfully create user account", null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> changePassword(@PathVariable String id, @RequestBody UserUpdateRequest userUpdateRequest) {
        userAccountService.changePassword(id, userUpdateRequest);
        return ResponseUtil.createResponse(HttpStatus.OK, "Successfully change password", null);
    }
}
