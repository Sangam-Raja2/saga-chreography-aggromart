package com.sangam.aggromart.order.controller;

import  com.sangam.aggromart.core.dto.OrderRequestDTO;
import com.sangam.aggromart.core.dto.PurchaseOrderDTO;
import com.sangam.aggromart.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/create")
    public PurchaseOrderDTO createOrder(@RequestBody OrderRequestDTO orderRequestDto){
        return orderService.createOrder(orderRequestDto);
    }

    @GetMapping
    public List<PurchaseOrderDTO> getOrders(){
        return orderService.getAllOrders();
    }
}
