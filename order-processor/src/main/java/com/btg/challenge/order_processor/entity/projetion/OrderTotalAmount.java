package com.btg.challenge.order_processor.entity.projetion;

import java.math.BigDecimal;

public interface OrderTotalAmount {

    Long getCode();

    BigDecimal getTotalAmount();
}
