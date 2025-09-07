package com.btg.challenge.order_processor.listener;

import com.btg.challenge.order_processor.dto.ProcessOrderEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static com.btg.challenge.order_processor.config.RabbitMQConfig.ORDER_CREATED_QUEUE;

@Component
public class ProcessOrderListener {

    public ProcessOrderListener() {

    }

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listener(Message<ProcessOrderEvent> message) {
        System.out.println(message.getPayload());
    }
}
