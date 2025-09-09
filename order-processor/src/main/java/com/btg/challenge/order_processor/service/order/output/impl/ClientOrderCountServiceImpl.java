package com.btg.challenge.order_processor.service.order.output.impl;

import com.btg.challenge.order_processor.dto.output.ClientOrderCount;
import com.btg.challenge.order_processor.exception.NotFoundException;
import com.btg.challenge.order_processor.repository.OrderRepository;
import com.btg.challenge.order_processor.service.order.output.ClientOrderCountService;
import com.btg.challenge.order_processor.service.order.output.mapper.ClientOrderCountMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientOrderCountServiceImpl implements ClientOrderCountService {

    private OrderRepository repository;

    private ClientOrderCountMapper mapper;

    public ClientOrderCountServiceImpl (
            OrderRepository repository,
            ClientOrderCountMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ClientOrderCount getByClientCode(Long clientCode) {
        var result = repository.findClientOrderCountByClientCode(clientCode);
        return mapper.toDTO(result.orElseThrow(() -> new NotFoundException("Cliente não tem pedidos")));
    }

    public Page<ClientOrderCount> getAllPageable(Pageable pageable) {
        var result =  repository.findClientOrderCount(pageable);
        if(result.isEmpty()) {
            throw new NotFoundException("Nao existem dados para esta pagina");
        }
        return result.map(mapper::toDTO);
    }
}
