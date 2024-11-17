package com.refanzzzz.banksampahapp.constant.query;

public class TransactionQuery {
    public static final String CREATE_TRANSACTION = """
            INSERT INTO t_transaction 
                (id, customer_id, transaction_date) 
            VALUES (?1, ?2, ?3)
            """;

    public static final String CREATE_TRANSACTION_DETAIL = """
            INSERT INTO t_transaction_detail
                (id, price, total_trash_weight, transaction_id, trash_id)
            VALUES (?1, ?2, ?3, ?4, ?5)
            """;

    public static final String GET_ALL_TRANSACTION_WITH_PAGINATION = """
            SELECT
                tsc.id AS transactionId,
                tsc.transaction_date AS transactionDate,
                c.name AS customerName,
                td.price AS trashPrice,
                td.total_trash_weight AS totalTrashWeight,
                trs.name AS trashName
                    FROM t_transaction tsc
                        INNER JOIN m_customer c ON tsc.customer_id = c.id
                        INNER JOIN t_transaction_detail td ON td.transaction_id = tsc.id
                        INNER JOIN m_trash trs ON td.trash_id = trs.id LIMIT ?1 OFFSET ?2
            """;

    public static final String GET_TRANSACTION_COUNT = "SELECT COUNT(*) FROM t_transaction_detail";

    public static final String GET_TRANSACTION_BY_ID = "SELECT id, transaction_date, customer_id FROM t_transaction WHERE id = ?1";
}
