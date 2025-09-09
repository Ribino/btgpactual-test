package com.btg.challenge.order_processor.service.order.output;

import com.btg.challenge.order_processor.dto.output.ClientOrderCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientOrderCountService {

    ClientOrderCount getByClientCode(Long clientCode);

    Page<ClientOrderCount> getAllPageable(Pageable pageable);
}
