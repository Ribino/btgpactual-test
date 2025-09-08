package com.btg.challenge.order_processor.dto.input;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ProcessOrderEvent(
    @JsonProperty("codigoPedido") Long orderCode,
    @JsonProperty("codigoCliente") Long clientCode,
    @JsonProperty("itens") List<ProcessOrderItemEvent> items
) {

}
