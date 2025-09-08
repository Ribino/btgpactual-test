package com.btg.challenge.order_processor.service.order.process.impl;

import com.btg.challenge.order_processor.dto.input.ProcessOrderEvent;
import com.btg.challenge.order_processor.entity.Order;
import com.btg.challenge.order_processor.repository.OrderRepository;
import com.btg.challenge.order_processor.service.order.process.ProcessOrderCreateService;
import com.btg.challenge.order_processor.service.order.process.mapper.ProcessOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class ProcessOrderCreateServiceImp implements ProcessOrderCreateService {

    private final OrderRepository repository;

    private final ProcessOrderMapper processOrderMapper;

    public ProcessOrderCreateServiceImp(
            OrderRepository repository,
            ProcessOrderMapper processOrderMapper
    ) {
        this.repository = repository;
        this.processOrderMapper = processOrderMapper;
    }

    @Override
    public void createOrder(ProcessOrderEvent processOrderEvent) {
        Order order = processOrderMapper.toEntity(processOrderEvent);
        order.calculateTotalAmount();
        try {
            save(order);
        }
        catch (DataIntegrityViolationException e) {
            if(e.getMessage() != null && e.getMessage().contains("duplicate key")) {
                log.warn("Already exists a order for this code: {}", order.getCode());
            }
            else {
                throw  e;
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(Order order) {
        repository.save(order);
    }
}
