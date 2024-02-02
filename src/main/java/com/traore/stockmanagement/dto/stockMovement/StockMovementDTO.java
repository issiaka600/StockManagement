package com.traore.stockmanagement.dto.stockMovement;

import com.traore.stockmanagement.dto.departmentStore.DepartmentStoreOnlyDTO;
import com.traore.stockmanagement.dto.product.ProductDTO;
import com.traore.stockmanagement.enums.MovementType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class StockMovementDTO {
    private String id;
    private BigDecimal quantity;
    private Date date;
    private BigDecimal unitPrice;
    private Date expiryDate;
    private MovementType type;
    private DepartmentStoreOnlyDTO departmentStore;
    private ProductDTO product;
}
