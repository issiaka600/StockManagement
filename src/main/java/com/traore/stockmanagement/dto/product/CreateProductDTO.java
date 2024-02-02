package com.traore.stockmanagement.dto.product;

import com.traore.stockmanagement.dto.enterprise.UpdateEnterpriseDTO;
import com.traore.stockmanagement.dto.category.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CreateProductDTO {
    private String id;
    private String name;
    private BigDecimal unitPrice;
    private String photo;
    private BigDecimal quantityMin;
    private CategoryDTO category;
    private UpdateEnterpriseDTO enterprise;
}
