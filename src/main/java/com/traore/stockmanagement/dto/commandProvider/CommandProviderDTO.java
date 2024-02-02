package com.traore.stockmanagement.dto.commandProvider;

import com.traore.stockmanagement.dto.commandLineProvider.CommandLineProviderDTO;
import com.traore.stockmanagement.dto.provider.CreateProviderDTO;
import com.traore.stockmanagement.dto.user.CreateUserDTO;
import com.traore.stockmanagement.enums.CommandStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CommandProviderDTO {
    private String id;
    private Date date;
    private CommandStatus status;
    private List<CommandLineProviderDTO> commandLineProviders;
    private CreateProviderDTO provider;
    private CreateUserDTO user;
}
