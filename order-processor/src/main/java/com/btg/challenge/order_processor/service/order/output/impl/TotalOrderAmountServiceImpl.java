package com.btg.challenge.order_processor.service.order.output.impl;

import com.btg.challenge.order_processor.dto.output.TotalOrderAmount;
import com.btg.challenge.order_processor.exception.NotFoundException;
import com.btg.challenge.order_processor.repository.OrderRepository;
import com.btg.challenge.order_processor.repository.projection.OrderTotalAmountProjection;
import com.btg.challenge.order_processor.service.order.output.TotalOrderAmountService;
import com.btg.challenge.order_processor.service.order.output.mapper.TotalOrderAmountMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TotalOrderAmountServiceImpl implements TotalOrderAmountService {

    private final OrderRepository repository;

    private final TotalOrderAmountMapper mapper;

    public TotalOrderAmountServiceImpl(
        OrderRepository repository,
        TotalOrderAmountMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<TotalOrderAmount> getAllPageable(Pageable pageable) {
        var result = repository.findAllOrderTotalAmountProjection(pageable);
        if(result.isEmpty()) {
            throw new NotFoundException("Nao existem dados para esta pagina");
        }
        return result.map(mapper::toDTO);
    }

    @Override
    public TotalOrderAmount getByOrderCode(Long orderCode) {
        var result = repository.findByCode(orderCode, OrderTotalAmountProjection.class);
        return mapper.toDTO(result.orElseThrow(() -> new NotFoundException("Nao existe pedido para este codigo")));
    }
}
