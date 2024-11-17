package com.refanzzzz.banksampahapp.constant.query;

public class CustomerQuery {
    public static final String INSERT_CUSTOMER = """
            INSERT INTO m_customer
                (id, name, phone_number, address, gender, user_account_id) 
            VALUES (?1, ?2, ?3, ?4, ?5, ?6)
            """;

    public static final String DELETE_CUSTOMER_BY_ID = "DELETE FROM m_customer WHERE id = ?1";

    public static final String UPDATE_CUSTOMER = """
            UPDATE m_customer
                SET name = ?1, phone_number = ?2, address = ?3, gender = ?4
            WHERE id = ?5
            """;

    public static final String GET_ALL_CUSTOMER_WITH_PAGINATION =
            "SELECT c.id, c.name, c.phone_number, c.gender, c.address, u.username, u.role " +
                    "FROM m_customer c " +
                    "INNER JOIN m_user_account u ON c.user_account_id = u.id LIMIT ?1 OFFSET ?2";

    public static final String GET_CUSTOMER_BY_ID =
            "SELECT c.id, c.name, c.phone_number, c.gender, c.address, u.username, u.role " +
                    "FROM m_customer c " +
                    "INNER JOIN m_user_account u ON c.user_account_id = u.id WHERE c.id = ?1";

    public static final String GET_CUSTOMER_COUNT = "SELECT COUNT(*) FROM m_customer";
}

