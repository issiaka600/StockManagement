package com.traore.stockmanagement.dto.departmentStore;

import com.traore.stockmanagement.dto.enterprise.UpdateEnterpriseDTO;
import com.traore.stockmanagement.dto.lineStorage.LineStorageDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data @AllArgsConstructor @Builder
public class DepartmentStoreWithLineStoragesDTO {
    private Long id;
    private String name;
    private UpdateEnterpriseDTO enterprise;
    private List<LineStorageDTO> lineStorages;
}
