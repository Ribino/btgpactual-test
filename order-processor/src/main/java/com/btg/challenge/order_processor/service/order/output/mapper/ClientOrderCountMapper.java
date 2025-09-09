package com.btg.challenge.order_processor.service.order.output.mapper;

import com.btg.challenge.order_processor.dto.output.ClientOrderCount;
import com.btg.challenge.order_processor.repository.projection.ClientOrderCountProjection;
import org.springframework.stereotype.Component;

@Component
public class ClientOrderCountMapper {

    public ClientOrderCount toDTO(ClientOrderCountProjection projection) {
        return new ClientOrderCount(
                projection.getClientCode(),
                projection.getTotalOrders()
        );
    }
}
