package com.traore.stockmanagement.dto.sale;

import com.traore.stockmanagement.dto.saleLine.SaleLineDTO;
import com.traore.stockmanagement.dto.user.CreateUserDTO;
import com.traore.stockmanagement.enums.CommandStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class SaleDTO {
    private String id;
    private Date date;
    private CommandStatus status;
    private List<SaleLineDTO> saleLines;
    private CreateUserDTO user;
}
