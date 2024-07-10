package com.br.appointmax.api.dto;

import java.time.LocalDateTime;

public record FilterMessageRequestDTO(String status, LocalDateTime start, LocalDateTime end) {
}