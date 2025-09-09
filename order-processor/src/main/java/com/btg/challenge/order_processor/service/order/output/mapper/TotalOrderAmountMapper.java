package com.btg.challenge.order_processor.service.order.output.mapper;

import com.btg.challenge.order_processor.dto.output.TotalOrderAmount;
import com.btg.challenge.order_processor.repository.projection.OrderTotalAmountProjection;
import org.springframework.stereotype.Component;

@Component
public class TotalOrderAmountMapper {

    public TotalOrderAmount toDTO(OrderTotalAmountProjection projection) {
        return new TotalOrderAmount(
                projection.getCode(),
                projection.getTotalAmount()
        );
    }

}
