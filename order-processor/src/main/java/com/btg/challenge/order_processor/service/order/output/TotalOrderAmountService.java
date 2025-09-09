package com.btg.challenge.order_processor.service.order.output;

import com.btg.challenge.order_processor.dto.output.TotalOrderAmount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TotalOrderAmountService {

    Page<TotalOrderAmount> getAllPageable(Pageable pageable);

    TotalOrderAmount getByOrderCode(Long orderCode);
}
