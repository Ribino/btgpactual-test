package com.btg.challenge.order_processor.service.order.process.mapper;

import com.btg.challenge.order_processor.dto.input.ProcessOrderEvent;
import com.btg.challenge.order_processor.dto.input.ProcessOrderItemEvent;
import com.btg.challenge.order_processor.entity.Order;
import com.btg.challenge.order_processor.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProcessOrderMapper {

    public Order toEntity(ProcessOrderEvent processOrderEvent) {
        Order order = new Order();

        order.setCode(processOrderEvent.orderCode());
        order.setClientCode(processOrderEvent.clientCode());
        order.setItems(toOrderItemEntityList(processOrderEvent.items(), order));

        return order;
    }

    private List<OrderItem> toOrderItemEntityList(List<ProcessOrderItemEvent> processOrderItemEvents, Order order) {
        if (processOrderItemEvents == null) {
            return new ArrayList<>();
        }

        return processOrderItemEvents.stream().map(item -> toEntity(item, order)).toList();
    }

    private OrderItem toEntity(ProcessOrderItemEvent processOrderItemEvent, Order order) {
        OrderItem orderItem = new OrderItem();

        orderItem.setOrder(order);
        orderItem.setPrice(processOrderItemEvent.price());
        orderItem.setItemName(processOrderItemEvent.itemName());
        orderItem.setQuantity(processOrderItemEvent.quantity());

        return orderItem;
    }

}
