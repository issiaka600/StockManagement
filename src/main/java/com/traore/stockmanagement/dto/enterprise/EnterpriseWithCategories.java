package com.traore.stockmanagement.dto.enterprise;

import com.traore.stockmanagement.dto.category.CategoryDTO;
import com.traore.stockmanagement.dto.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class EnterpriseWithCategories {
    private Long id;
    private String name;
    private String logoId;
    private List<CategoryDTO> categories;
}
