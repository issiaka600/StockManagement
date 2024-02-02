package com.traore.stockmanagement.dto.commandLineProvider;

import com.traore.stockmanagement.dto.commandProvider.CommandProviderOnlyDTO;
import com.traore.stockmanagement.dto.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CommandLineProviderDTO {
    private String id;
    private BigDecimal unitPrice;
    private BigDecimal quantity;
    private ProductDTO product;
    private CommandProviderOnlyDTO commandProvider;
}
