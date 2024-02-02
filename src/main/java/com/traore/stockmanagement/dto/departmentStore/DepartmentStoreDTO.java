package com.traore.stockmanagement.dto.departmentStore;

import com.traore.stockmanagement.dto.enterprise.UpdateEnterpriseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @AllArgsConstructor @Builder
public class DepartmentStoreDTO {
    private Long id;
    private String name;
    private UpdateEnterpriseDTO enterprise;
}
