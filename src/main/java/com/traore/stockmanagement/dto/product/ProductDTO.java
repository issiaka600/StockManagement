package com.traore.stockmanagement.dto.product;

import com.traore.stockmanagement.dto.category.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data @AllArgsConstructor @Builder
public class ProductDTO {
    private String id;
    private String name;
    private BigDecimal unitPrice;
    private BigDecimal quantityMin;
    private String photo;
    private CategoryDTO category;
}
