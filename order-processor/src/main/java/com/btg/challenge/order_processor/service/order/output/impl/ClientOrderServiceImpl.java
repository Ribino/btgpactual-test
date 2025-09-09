package com.btg.challenge.order_processor.service.order.output.impl;

import com.btg.challenge.order_processor.dto.output.clientOrder.ClientOrderDTO;
import com.btg.challenge.order_processor.entity.Order;
import com.btg.challenge.order_processor.exception.NotFoundException;
import com.btg.challenge.order_processor.repository.OrderRepository;
import com.btg.challenge.order_processor.service.order.output.ClientOrderService;
import com.btg.challenge.order_processor.service.order.output.mapper.ClientOrdersMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientOrderServiceImpl implements ClientOrderService {

    private final OrderRepository repository;

    private final ClientOrdersMapper mapper;

    public ClientOrderServiceImpl(
        OrderRepository repository,
        ClientOrdersMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ClientOrderDTO getByClientCode(Long clientCode, Pageable pageable) {
        var result = repository.findByClientCode(clientCode, pageable);
        if(result.isEmpty()) {
            throw new NotFoundException("Nao existem pedidos para este cliente");
        }

        return mapper.toDto(clientCode, result.getContent(), result.getTotalElements());
    }

    @Override
    public List<ClientOrderDTO> getAll() {
        var result = repository
                .findAll()
                .stream().collect(Collectors.groupingBy(Order::getClientCode));

        if(result.isEmpty()) {
            throw new NotFoundException("Nao existem dados para esta pagina");
        }

        List<ClientOrderDTO> clientOrderDTOS = new ArrayList<>();

        result.forEach((clientCode, orders) -> clientOrderDTOS.add(mapper.toDto(clientCode, orders, Integer.toUnsignedLong(orders.size()))));

        return clientOrderDTOS;
    }
}
