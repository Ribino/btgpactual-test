package com.btg.challenge.order_processor.dto.output;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ClientOrderCount(
        @JsonProperty("codigoCliente") Long costumerCode,
        @JsonProperty("quantidadePedidos")  Long orderCount
) {
}
