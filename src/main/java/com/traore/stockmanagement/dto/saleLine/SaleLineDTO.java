package com.traore.stockmanagement.dto.saleLine;

import com.traore.stockmanagement.dto.product.ProductDTO;
import com.traore.stockmanagement.dto.sale.SaleOnlyDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class SaleLineDTO {
    private String id;
    private BigDecimal unitPrice;
    private BigDecimal quantity;
    private ProductDTO product;
    private SaleOnlyDTO sale;
}
