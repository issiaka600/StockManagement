package com.traore.stockmanagement.dto.sale;

import com.traore.stockmanagement.enums.CommandStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class SaleOnlyDTO {
    private String id;
    private Date date;
    private CommandStatus status;
}
