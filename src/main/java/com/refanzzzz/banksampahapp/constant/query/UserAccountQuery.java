package com.refanzzzz.banksampahapp.constant.query;

public class UserAccountQuery {
    public static final String INSERT_USER = "INSERT INTO m_user_account (username, password, role) VALUES (?1, ?2, ?3)";

    public static final String UPDATE_USER_PASSWORD = "UPDATE m_user_account SET password = ?1 WHERE id = ?2";
}
