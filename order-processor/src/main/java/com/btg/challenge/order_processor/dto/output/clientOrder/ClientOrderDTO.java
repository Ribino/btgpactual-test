package com.btg.challenge.order_processor.dto.output.clientOrder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ClientOrderDTO(
        @JsonProperty("codigoCliente") Long clientCode,
        @JsonProperty("pedidos") List<OrderDTO> orders,
        @JsonIgnore Long totalElements
) { }


