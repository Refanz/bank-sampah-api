package com.refanzzzz.banksampahapp.constant;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("Admin"),
    CUSTOMER("Customer");

    private final String name;

    UserRole(String name) {
        this.name = name;
    }

    public static String getNameByUserRole(String userRole) {
        for (UserRole role : values()) {
            if (role.name().equalsIgnoreCase(userRole))
                return role.getName();
        }

        return null;
    }

    public static UserRole getUserRoleByName(String name) {
        for (UserRole role : values()) {
            if (role.name().equalsIgnoreCase(name)) return role;
        }

        return null;
    }
}
