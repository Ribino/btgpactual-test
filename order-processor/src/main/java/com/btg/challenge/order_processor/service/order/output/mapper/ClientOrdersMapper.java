package com.btg.challenge.order_processor.service.order.output.mapper;

import com.btg.challenge.order_processor.dto.output.clientOrder.ClientOrderDTO;
import com.btg.challenge.order_processor.dto.output.clientOrder.OrderDTO;
import com.btg.challenge.order_processor.dto.output.clientOrder.OrderItemDTO;
import com.btg.challenge.order_processor.entity.Order;
import com.btg.challenge.order_processor.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientOrdersMapper {

    public ClientOrderDTO toDto(Long clientCode, List<Order> order, Long totalElements) {
        return new ClientOrderDTO(
                clientCode,
                order.stream().map(this::toDto).toList(),
                totalElements
        );
    }

    public OrderDTO toDto(Order order) {
        var items = order.getItems().stream().map(this::toDto).toList();
        return new OrderDTO(
                order.getCode(),
                order.getCreatedAt(),
                items
        );
    }


    public OrderItemDTO toDto(OrderItem order) {
        return new OrderItemDTO(
                order.getItemName(),
                order.getQuantity(),
                order.getPrice()
        );
    }
}
