package com.sangam.common.dto;

import com.sangam.common.event.OrderStatus;
import com.sangam.common.event.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDTO {
    private String id;
    private String userId;
    private ProductResponseDTO productDetails;
    private Integer price;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
}
