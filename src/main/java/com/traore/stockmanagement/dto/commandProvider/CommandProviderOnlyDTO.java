package com.traore.stockmanagement.dto.commandProvider;

import com.traore.stockmanagement.enums.CommandStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CommandProviderOnlyDTO {
    private String id;
    private Date date;
    private CommandStatus status;
}
