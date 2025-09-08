package com.btg.challenge.order_processor.service.order.process;

import com.btg.challenge.order_processor.dto.input.ProcessOrderEvent;
import com.btg.challenge.order_processor.entity.Order;

public interface ProcessOrderCreateService {

    void createOrder(ProcessOrderEvent processOrderEvent);

    void save(Order order);
}
