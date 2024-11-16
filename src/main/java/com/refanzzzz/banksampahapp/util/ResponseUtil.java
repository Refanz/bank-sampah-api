package com.refanzzzz.banksampahapp.util;

import com.refanzzzz.banksampahapp.dto.response.ApiResponse;
import com.refanzzzz.banksampahapp.dto.response.PagingResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {
    public static <T> ResponseEntity<ApiResponse<?>> createResponse(HttpStatus httpStatus, String message, T data) {
        ApiResponse<T> apiResponse = ApiResponse.<T>builder()
                .status(httpStatus.value())
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(httpStatus).body(apiResponse);
    }

    public static <T> ResponseEntity<ApiResponse<?>> createResponseWithPaging(HttpStatus httpStatus, String message, T data, PagingResponse paging) {
        ApiResponse<T> apiResponse = ApiResponse.<T>builder()
                .status(httpStatus.value())
                .message(message)
                .data(data)
                .paging(paging)
                .build();

        return ResponseEntity.status(httpStatus).body(apiResponse);
    }
}
