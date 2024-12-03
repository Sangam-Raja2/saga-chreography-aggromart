package com.sangam.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Map;

@Document(value="product")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private Long productId;
    private String name;
    private String category;
    private Map<String ,String> description;
    private Float price;

}
