package com.btg.challenge.order_processor.service.order.output;

import com.btg.challenge.order_processor.dto.output.clientOrder.ClientOrderDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientOrderService {

    ClientOrderDTO getByClientCode(Long clientCode, Pageable pageable);

    List<ClientOrderDTO> getAll();

}
