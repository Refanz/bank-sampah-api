package com.refanzzzz.banksampahapp.constant.query;

public class UserAccountQuery {
    public static final String INSERT_USER = "INSERT INTO m_user_account (id, username, password, role) VALUES (?1, ?2, ?3, ?4)";

    public static final String UPDATE_USER_PASSWORD = "UPDATE m_user_account SET password = ?1 WHERE id = ?2";

    public static final String GET_USER_ACCOUNT_BY_ID = "SELECT id, username, password, role FROM m_user_account WHERE id = ?1";

    public static final String GET_USER_ACCOUNT_BY_USERNAME = "SELECT id, username, password, role FROM m_user_account WHERE username = ?1";
}
