package com.traore.stockmanagement.dto.lineStorage;

import com.traore.stockmanagement.dto.departmentStore.DepartmentStoreOnlyDTO;
import com.traore.stockmanagement.dto.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data @AllArgsConstructor @Builder
public class LineStorageDTO {
    private String id;
    private BigDecimal quantity;
    private DepartmentStoreOnlyDTO departmentStore;
    private ProductDTO product;
}
