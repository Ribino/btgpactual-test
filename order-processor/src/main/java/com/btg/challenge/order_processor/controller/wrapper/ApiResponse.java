package com.btg.challenge.order_processor.controller.wrapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

public record ApiResponse<T> (
     boolean success,
     T data,
     Pagination pagination,
     ApiError error,
     Instant timestamp
){
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>(
                true,
                data,
                null,
                null,
                Instant.now()
        );
    }

    public static <T> ApiResponse<List<T>> ok(Page<T> page) {
        return new ApiResponse<>(
                true,
                page.getContent(),
                new Pagination(page.getNumber(), page.getSize(), page.getTotalElements()),
                null,
                Instant.now()
        );
    }

    public static <T> ApiResponse<T> error(String code, String message) {
        return new ApiResponse<T>(
                false,
                null,
                null,
                new ApiError(code, message),
                Instant.now()
        );
    }

    public record Pagination(int page, int numberOfPage, long totalElements) {}
    public record ApiError(String code, String message) {}
}
