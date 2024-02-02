package com.traore.stockmanagement.dto.CommandLineClient;

import com.traore.stockmanagement.dto.CommandClient.CommandClientOnlyDTO;
import com.traore.stockmanagement.dto.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CommandLineClientDTO {
    private String id;
    private BigDecimal unitPrice;
    private BigDecimal quantity;
    private ProductDTO product;
    private CommandClientOnlyDTO commandClient;
}
