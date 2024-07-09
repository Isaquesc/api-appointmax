package com.br.appointmax.model.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record FilterMessageRequestDTO(String status, LocalDateTime start, LocalDateTime end) {
}