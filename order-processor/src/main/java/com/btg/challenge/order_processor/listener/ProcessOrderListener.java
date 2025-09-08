package com.btg.challenge.order_processor.listener;

import com.btg.challenge.order_processor.dto.input.ProcessOrderEvent;
import com.btg.challenge.order_processor.service.order.process.ProcessOrderCreateService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.btg.challenge.order_processor.config.RabbitMQConfig.ORDER_CREATED_QUEUE;

@Component
public class ProcessOrderListener {

    private ProcessOrderCreateService orderCreateService;

    public ProcessOrderListener(ProcessOrderCreateService orderCreateService) {
        this.orderCreateService = orderCreateService;
    }

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listener(Message<ProcessOrderEvent> message) {
       ProcessOrderEvent processOrderEvent = message.getPayload();
       orderCreateService.createOrder(processOrderEvent);
    }
}
