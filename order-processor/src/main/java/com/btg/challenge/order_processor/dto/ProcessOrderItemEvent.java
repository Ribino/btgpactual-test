package com.btg.challenge.order_processor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ProcessOrderItemEvent (
        @JsonProperty("produto") String itemName,
        @JsonProperty("quantidade") int quantity,
        @JsonProperty("preco") BigDecimal price
) {
}
