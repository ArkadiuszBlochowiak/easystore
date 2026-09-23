package com.easybytes.easystore.dto;

public record ContactResponseDto(
        Long contactId,
        String name,
        String email,
        String mobileNumber,
        String message,
        String status
) {
}
