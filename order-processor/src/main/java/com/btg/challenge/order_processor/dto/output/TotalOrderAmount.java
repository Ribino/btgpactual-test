package com.btg.challenge.order_processor.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record TotalOrderAmount(
        @JsonProperty("codigoPedido") Long orderCode,
        @JsonProperty("valorTotalPedido") BigDecimal totalAmount
) {
}
