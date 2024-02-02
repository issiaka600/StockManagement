package com.traore.stockmanagement.mapper;

import com.traore.stockmanagement.dto.CommandClient.CommandClientDTO;
import com.traore.stockmanagement.dto.CommandClient.CommandClientOnlyDTO;
import com.traore.stockmanagement.model.CommandClient;
import com.traore.stockmanagement.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandClientMapper {


    public CommandClientDTO fromEntity(CommandClient commandClient){
        if(commandClient != null){
            return CommandClientDTO.builder()
                    .id(commandClient.getId())
                    .date(commandClient.getDate())
                    .status(commandClient.getStatus())
                    .commandLineClients(
                            CommandLineClientMapper.fromEntity(
                                    commandClient.getCommandLineClients()
                            )
                    )
                    .client(
                            ClientMapper.fromEntityToCreateClientDTOStatic(
                                    commandClient.getClient()
                            )
                    )
                    .user(
                            UserMapper.FromEntityToCreateUserDTOStatic(
                                    commandClient.getUser()
                            )
                    )
                    .build();
        }
        return null;
    }

    public CommandClient toEntity(CommandClientDTO dto){
        if(dto != null){
            return CommandClient.builder()
                    .date(dto.getDate())
                    .status(dto.getStatus())
                    .client(
                            ClientMapper.toEntityStatic(
                                    dto.getClient()
                            )
                    )
                    .user(
                            UserMapper.fromCreateUserDTOtoEntityStatic(
                                    dto.getUser()
                            )
                    )
                    .build();
        }
        return null;
    }

    public static CommandClient fromCommandClientOnlyToEntity(CommandClientOnlyDTO dto){
        if(dto != null){
            return CommandClient.builder()
                    .id(dto.getId())
                    .date(dto.getDate())
                    .status(dto.getStatus())
                    .build();
        }
        return null;
    }

    public static CommandClientDTO fromEntityStatic(CommandClient commandClient){
        if(commandClient != null){
            return CommandClientDTO.builder()
                    .id(commandClient.getId())
                    .date(commandClient.getDate())
                    .status(commandClient.getStatus())
                    .commandLineClients(
                            CommandLineClientMapper.fromEntity(
                                    commandClient.getCommandLineClients()
                            )
                    )
                    .client(
                            ClientMapper.fromEntityToCreateClientDTOStatic(
                                    commandClient.getClient()
                            )
                    )
                    .user(
                            UserMapper.FromEntityToCreateUserDTOStatic(
                                    commandClient.getUser()
                            )
                    )
                    .build();
        }
        return null;
    }

    public static CommandClientOnlyDTO fromEntityToCommandClientOnlyDTO(CommandClient commandClient){
        if(commandClient != null){
            return CommandClientOnlyDTO.builder()
                    .id(commandClient.getId())
                    .date(commandClient.getDate())
                    .status(commandClient.getStatus())
                    .build();
        }
        return null;
    }

    public static List<CommandClientDTO> fromEntity(List<CommandClient> commandClients){
        if(commandClients != null){
            List<CommandClientDTO> dtos = new ArrayList<>();
            for (CommandClient commandClient : commandClients){
                dtos.add(
                        fromEntityStatic(commandClient)
                );
            }
            return dtos;
        }
        return null;
    }
}
