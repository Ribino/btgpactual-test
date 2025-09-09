package com.btg.challenge.order_processor.dto.output.clientOrder;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(
        @JsonProperty("codigoProduto") Long orderCode,
        @JsonProperty("criadoEm") LocalDateTime createAt,
        @JsonProperty("itens") List<OrderItemDTO> items
        ) {
}
