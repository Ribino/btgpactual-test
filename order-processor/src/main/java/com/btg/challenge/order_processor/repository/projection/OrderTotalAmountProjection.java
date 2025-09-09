package com.btg.challenge.order_processor.repository.projection;

import java.math.BigDecimal;

public interface OrderTotalAmountProjection {

    Long getCode();

    BigDecimal getTotalAmount();
}
