package com.btg.challenge.order_processor.repository.projection;

public interface ClientOrderCountProjection {
    Long getClientCode();
    Long getTotalOrders();
}
