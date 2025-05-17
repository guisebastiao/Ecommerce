package com.guisebastiao.api.controllers.dtos;

public record PagingDTO(
        long totalItems,
        long totalPages,
        long currentPage,
        long itemsPerPage
) {
}
