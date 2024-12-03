package com.sangam.aggromart.common.dto;

import com.sangam.aggromart.common.event.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class OrderResponseDTO {

        private Long userId;
        private Long productId;
        private Double amount;
        private Long orderId;
        private OrderStatus orderStatus;
    }