package com.sangam.aggromart.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    private Long userId;
    private Long productId;
    private Double amount;
    private Long orderId;
}
