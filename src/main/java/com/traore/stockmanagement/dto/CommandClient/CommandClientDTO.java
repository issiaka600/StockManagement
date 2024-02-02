package com.traore.stockmanagement.dto.CommandClient;

import com.traore.stockmanagement.dto.CommandLineClient.CommandLineClientDTO;
import com.traore.stockmanagement.dto.client.CreateClientDTO;
import com.traore.stockmanagement.dto.user.CreateUserDTO;
import com.traore.stockmanagement.enums.CommandStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CommandClientDTO {
    private String id;
    private Date date;
    private CommandStatus status;
    private List<CommandLineClientDTO> commandLineClients;
    private CreateClientDTO client;
    private CreateUserDTO user;
}
