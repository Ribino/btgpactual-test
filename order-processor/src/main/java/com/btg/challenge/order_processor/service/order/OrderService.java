package com.btg.challenge.order_processor.service.order;

import com.btg.challenge.order_processor.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Order getById(Long id);

    boolean existsByCode();

    Page<Order> getAll(Pageable pageable);
}
