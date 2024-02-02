package com.traore.stockmanagement.dto.departmentStore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @AllArgsConstructor @Builder
public class DepartmentStoreOnlyDTO {
    private Long id;
    private String name;
}
