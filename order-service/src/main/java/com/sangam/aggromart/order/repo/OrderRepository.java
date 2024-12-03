package com.sangam.aggromart.order.repo;

import com.sangam.aggromart.order.entity.PurchaseOrder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<PurchaseOrder,Long> {
}
