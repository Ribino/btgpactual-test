package com.btg.challenge.order_processor.dto.output.clientOrder;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record OrderItemDTO(
        @JsonProperty("produto") String itemName,
        @JsonProperty("quantidade")  Integer quantity,
        @JsonProperty("preco") BigDecimal price
) { }
