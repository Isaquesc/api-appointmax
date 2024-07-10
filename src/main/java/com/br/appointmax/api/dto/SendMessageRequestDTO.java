package com.br.appointmax.api.dto;

import java.util.List;

public record SendMessageRequestDTO(List<Long> clientIds, String messageContent) {
}