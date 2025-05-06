package com.nandan.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String code;
    private LocalDateTime timestamp;
    private String path;
}
