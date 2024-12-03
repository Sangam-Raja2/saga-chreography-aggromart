package com.sangam.aggromart.product.controller;

import com.sangam.aggromart.common.dto.ProductRequestDTO;
import com.sangam.aggromart.common.dto.ProductResponseDTO;
import com.sangam.aggromart.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Long> createProduct(@Valid @RequestBody ProductRequestDTO productRequest) {
        Long id = productService.createProduct(productRequest);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping(value = "all", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ProductResponseDTO>> getProducts(){
        List<ProductResponseDTO> allProducts = productService.getAllProducts();
        return new ResponseEntity<>(allProducts,HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable(value = "id") Long id){
        ProductResponseDTO product = productService.getProduct(id);
        return new ResponseEntity<>(product,HttpStatus.FOUND);
    }

    @GetMapping(value = "search", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<ProductResponseDTO>> getProductsByCategory(
            @RequestParam(value = "category",required = false,defaultValue = "all") String id,
            @RequestParam(value = "startRange",required = false,defaultValue = "-1") Float startRange,
            @RequestParam(value = "endRange",required = false,defaultValue = "-1") Float endRange){
        List<ProductResponseDTO> allProducts = productService.getProductsBySearchKey(id,startRange,endRange);
        return new ResponseEntity<>(allProducts,HttpStatus.FOUND);
    }


    @GetMapping(value = "categories", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<List<String>> getProductCategories(){
        List<String> allProducts = productService.getProductCategories();
        return new ResponseEntity<>(allProducts,HttpStatus.FOUND);
    }

    @DeleteMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Boolean> deleteProduct(@PathVariable(value = "id") Long id){
        Boolean isDeleted = productService.deleteProduct(id);
        return new ResponseEntity<>(isDeleted,HttpStatus.OK);

    }
}
