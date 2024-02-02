package com.traore.stockmanagement.dto.departmentStore;


import com.traore.stockmanagement.dto.enterprise.UpdateEnterpriseDTO;
import com.traore.stockmanagement.dto.stockMovement.StockMovementDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class DepartmentStoreWithStockMovementDTO {
    private Long id;
    private String name;
    private UpdateEnterpriseDTO enterprise;
    private List<StockMovementDTO> stockMovements;
}
