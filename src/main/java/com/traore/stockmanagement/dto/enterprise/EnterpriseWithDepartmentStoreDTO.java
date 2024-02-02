package com.traore.stockmanagement.dto.enterprise;

import com.traore.stockmanagement.dto.departmentStore.DepartmentStoreOnlyDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data @AllArgsConstructor @Builder
public class EnterpriseWithDepartmentStoreDTO {
    private Long id;
    private String name;
    private String logoId;
    private List<DepartmentStoreOnlyDTO> departmentStores;
}
