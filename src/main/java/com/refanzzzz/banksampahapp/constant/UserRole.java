package com.refanzzzz.banksampahapp.constant;

public enum UserRole {
    ADMIN("Admin"),
    CUSTOMER("Customer");

    private final String name;

    UserRole(String name) {
        this.name = name;
    }
}
