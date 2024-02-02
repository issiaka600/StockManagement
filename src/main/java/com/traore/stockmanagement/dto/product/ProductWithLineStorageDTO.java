package com.traore.stockmanagement.dto.product;

import com.traore.stockmanagement.dto.category.CategoryDTO;
import com.traore.stockmanagement.dto.lineStorage.LineStorageDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ProductWithLineStorageDTO {
    private String id;
    private String name;
    private BigDecimal unitPrice;
    private String photo;
    private BigDecimal quantityMin;
    private CategoryDTO category;
    private List<LineStorageDTO> lineStorages;
}
