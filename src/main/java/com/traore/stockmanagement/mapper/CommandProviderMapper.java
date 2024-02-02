package com.traore.stockmanagement.mapper;

import com.traore.stockmanagement.dto.commandProvider.CommandProviderDTO;
import com.traore.stockmanagement.dto.commandProvider.CommandProviderOnlyDTO;
import com.traore.stockmanagement.model.CommandProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandProviderMapper {
    public static CommandProvider toEntityStatic(CommandProviderOnlyDTO dto){
        if(dto != null){
            return CommandProvider.builder()
                    .id(dto.getId())
                    .date(dto.getDate())
                    .status(dto.getStatus())
                    .build();
        }
        return null;
    }


    public CommandProvider toEntity(CommandProviderDTO dto){
        if(dto != null){
            return CommandProvider.builder()
                    .id(dto.getId())
                    .date(dto.getDate())
                    .status(dto.getStatus())
                    .provider(
                            ProviderMapper.fromCreateProviderDTOToEntityStatic(
                                    dto.getProvider()
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


    public static CommandProviderDTO fromEntity(CommandProvider commandProvider){
        if(commandProvider != null){
            return CommandProviderDTO.builder()
                    .id(commandProvider.getId())
                    .date(commandProvider.getDate())
                    .status(commandProvider.getStatus())
                    .commandLineProviders(
                        CommandLineProviderMapper.fromEntity(
                                commandProvider.getCommandLineProviders()
                        )
                    )
                    .provider(
                            ProviderMapper.fromEntityToCreateProviderDTOStatic(
                                    commandProvider.getProvider()
                            )
                    )
                    .user(
                            UserMapper.FromEntityToCreateUserDTOStatic(
                                    commandProvider.getUser()
                            )
                    )
                    .build();
        }
        return null;
    }

    public static List<CommandProviderDTO> fromEntity(List<CommandProvider> commandProviders){
        if(commandProviders != null){
            List<CommandProviderDTO> dtos = new ArrayList<>();
            for (CommandProvider commandProvider : commandProviders){
                dtos.add(
                        fromEntity(commandProvider)
                );
            }
            return dtos;
        }
        return null;
    }

    public static CommandProviderOnlyDTO fromEntityToCommandProviderOnlyDTO(CommandProvider commandProvider){
        if(commandProvider != null){
            return CommandProviderOnlyDTO.builder()
                    .id(commandProvider.getId())
                    .date(commandProvider.getDate())
                    .status(commandProvider.getStatus())
                    .build();
        }
        return null;
    }

}
