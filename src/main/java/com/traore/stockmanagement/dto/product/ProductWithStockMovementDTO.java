package com.traore.stockmanagement.dto.product;

import com.traore.stockmanagement.dto.category.CategoryDTO;
import com.traore.stockmanagement.dto.stockMovement.StockMovementDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ProductWithStockMovementDTO {
    private String id;
    private String name;
    private BigDecimal unitPrice;
    private BigDecimal quantityMin;
    private String photo;
    private CategoryDTO category;
    private List<StockMovementDTO> stockMovements;
}
