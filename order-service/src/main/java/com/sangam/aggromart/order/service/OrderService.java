package com.sangam.aggromart.order.service;

import com.sangam.aggromart.core.dto.OrderRequestDTO;
import com.sangam.aggromart.core.dto.ProductResponseDTO;
import com.sangam.aggromart.core.event.OrderStatus;
import com.sangam.aggromart.core.dto.PurchaseOrderDTO;
import com.sangam.aggromart.core.event.PaymentStatus;
import com.sangam.aggromart.order.entity.PurchaseOrder;
import com.sangam.aggromart.order.repo.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderService {
    @Value("${product_end_point}")
    private String endPoint;

    @Autowired
    Environment env;

    @Autowired
    @Qualifier("normalRestTemplate")
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("customRestTemplate")
    private RestTemplate customRestTemplate;


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusPublisher orderStatusPublisher;

    @Transactional
    public PurchaseOrderDTO createOrder(OrderRequestDTO orderRequestDto) {
        String baseUrl = env.getProperty("product_api");
        String url = baseUrl + "/" + endPoint + "/" + orderRequestDto.getProductId();
        log.info("api calling with endpoint: {}", url);
        ProductResponseDTO product = customRestTemplate.getForObject(url, ProductResponseDTO.class);
        if (null != product && null != product.getProductId()) {
            PurchaseOrder order = orderRepository.save(converttoToEntity(orderRequestDto));
            //produce kafka event with status ORDER_CREATED
            orderStatusPublisher.publishOrderEvent(orderRequestDto, OrderStatus.ORDER_CREATED);
            return buildPurchaseOrderDTO(order, product);
        }
        return null;
    }

    public List<PurchaseOrderDTO> getAllOrders() {
        List<PurchaseOrder> allOrder = orderRepository.findAll();
        return allOrder.stream()
                .map(order ->buildPurchaseOrderDTO(order, ProductResponseDTO.builder().productId(order.getProductId()).build()))
                .collect(Collectors.toList());
    }


    private PurchaseOrder converttoToEntity(OrderRequestDTO dto) {
        return PurchaseOrder.builder().id(UUID.randomUUID().toString()).productId(dto.getProductId()).
                userId(dto.getUserId()).orderStatus(OrderStatus.ORDER_CREATED).build();
    }

    private PurchaseOrderDTO buildPurchaseOrderDTO(PurchaseOrder purchaseOrder, ProductResponseDTO product) {
        return PurchaseOrderDTO.builder().id(purchaseOrder.getId()).userId(purchaseOrder.getUserId()).
                productDetails(product != null ? product : new ProductResponseDTO())
                .orderStatus(OrderStatus.ORDER_CREATED).paymentStatus(PaymentStatus.PAYMENT_PENDING).build();
    }
}