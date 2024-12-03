package com.sangam.aggromart.product.service;

import com.sangam.aggromart.common.dto.ProductRequestDTO;
import com.sangam.aggromart.common.dto.ProductResponseDTO;
import com.sangam.aggromart.common.util.IdGenerator;
import com.sangam.aggromart.product.entity.Product;
import com.sangam.aggromart.product.exception.ProductException;
import com.sangam.aggromart.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Long createProduct(ProductRequestDTO request) {
        Product product = Product.builder()
                .productId(IdGenerator.getID())
                .name(request.getName())
                .category(request.getCategory())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
        Product save = productRepository.save(product);
        return save.getProductId();
    }

    public List<ProductResponseDTO> getAllProducts() {
        List<Product> allProduct = productRepository.findAll();
        return allProduct.stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    public ProductResponseDTO getProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent())
          return   mapToProductResponse(product.get());
        else
            throw new ProductException(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(),
                    "product not found with given Id :" + id, "product not found with given Id :" + id);
    }

    public Boolean deleteProduct(Long id) {
        productRepository.deleteById(id);
        return productRepository.findById(id).isPresent() ? false : true;
    }


    private ProductResponseDTO mapToProductResponse(Product product) {
        return ProductResponseDTO.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .category(product.getCategory())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }


    public List<String> getProductCategories() {
        return productRepository.findDistinctCategories();
    }


    public List<ProductResponseDTO> getProductsBySearchKey(String category, Float startRange, Float endRange) {
        if (category.equalsIgnoreCase("all") && startRange < 0 && endRange < 0)
            return productRepository.findAll().stream().map(this::mapToProductResponse).collect(Collectors.toList());
        else if (!category.equalsIgnoreCase("all") && startRange < 0 && endRange < 0)
            return productRepository.findByCategory(category).stream().map(this::mapToProductResponse).collect(Collectors.toList());
        else if (category.equalsIgnoreCase("all") && startRange > 0 && endRange > 0)
            return productRepository.findProductBetweenPrice(startRange, endRange).stream()
                    .map(this::mapToProductResponse).collect(Collectors.toList());
        else
             return productRepository.findByCategoryAndPrice(category,startRange, endRange).stream()
                     .map(this::mapToProductResponse).collect(Collectors.toList());
    }
}
