package com.sangam.aggromart.order.service;

import com.sangam.aggromart.core.dto.OrderRequestDTO;
import com.sangam.aggromart.core.event.OrderEvent;
import com.sangam.aggromart.core.event.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class OrderStatusPublisher {

    @Autowired
    private Sinks.Many<OrderEvent> orderSinks;


    public void publishOrderEvent(OrderRequestDTO orderRequestDto, OrderStatus orderStatus){
        OrderEvent orderEvent=new OrderEvent(orderRequestDto,orderStatus);
        orderSinks.tryEmitNext(orderEvent);
    }
}
