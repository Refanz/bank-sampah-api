package com.refanzzzz.banksampahapp.util;

public class PagingUtil {
    public static int getTotalPage(int totalItems, int limit) {
        return (int) Math.ceil((double) totalItems / (double) limit);
    }

    public static int generateOffset(int limit, int page) {
        return limit * (page - 1);
    }
}
