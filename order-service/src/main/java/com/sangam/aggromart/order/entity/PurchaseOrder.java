package com.sangam.aggromart.order.entity;
import com.sangam.aggromart.core.event.OrderStatus;
import com.sangam.aggromart.core.event.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(value="order")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {
    private String id;
    private String userId;
    private String productId;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
}
