package com.traore.stockmanagement.dto.category;

import com.traore.stockmanagement.dto.enterprise.UpdateEnterpriseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @AllArgsConstructor @Builder
public class CategoryDTO {
    private Long id;
    private String name;
    private UpdateEnterpriseDTO enterprise;
}
