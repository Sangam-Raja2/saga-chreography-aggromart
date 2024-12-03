package com.sangam.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    @NotBlank(message = "product name is Mandatory")
    @NotNull(message = "product name Can't be Null")
    @Size(min = 2, message = "product name must have atleast two characters")
    private String name;
    @NotBlank(message = "product catogary is Mandatory")
    @NotNull(message = "product catogary Can't be Null")
    @Size(min = 2, message = "product catogary must have atleast two characters")
    private String category;
    private Map<String ,String> description;
    @NotNull(message = "product price Can't be Null")
    @Min(0)
    private Float price;
}
