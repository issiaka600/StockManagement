package com.traore.stockmanagement.dto.CommandClient;

import com.traore.stockmanagement.enums.CommandStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommandClientOnlyDTO {
    private String id;
    private Date date;
    private CommandStatus status;
}
