package com.refanzzzz.banksampahapp.constant.query;

public class TrashQuery {
    public static final String INSERT_TRASH = "INSERT INTO m_trash (id, name, unit, price) VALUES (?1, ?2, ?3, ?4)";

    public static final String DELETE_TRASH_BY_ID = "DELETE FROM m_trash WHERE id = ?1";

    public static final String UPDATE_TRASH = "UPDATE m_trash SET name = ?1, unit = ?2, price = ?3 WHERE id = ?4";

    public static final String GET_ALL_TRASH_WITH_PAGINATION = "SELECT id, name, unit, price FROM m_trash LIMIT ?1 OFFSET ?2";

    public static final String GET_TRASH_COUNT = "SELECT COUNT(*) FROM m_trash";

    public static final String GET_TRASH_BY_ID = "SELECT id, name, unit, price FROM m_trash WHERE id = ?1";
}
