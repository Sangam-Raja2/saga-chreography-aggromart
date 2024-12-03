package com.sangam.product.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private HttpStatus status;
    private int code;
    private String message;
    private String developerMessage;


}