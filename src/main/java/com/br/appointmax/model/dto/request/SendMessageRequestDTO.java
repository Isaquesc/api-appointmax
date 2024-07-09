package com.br.appointmax.model.dto.request;

import java.util.List;

public record SendMessageRequestDTO(List<Long> clientIds, String messageContent) {
}