package com.traore.stockmanagement.dto.category;

import com.traore.stockmanagement.dto.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class CategoryDetailsDTO {
    private Long id;
    private String name;
   List<ProductDTO> products;
}
