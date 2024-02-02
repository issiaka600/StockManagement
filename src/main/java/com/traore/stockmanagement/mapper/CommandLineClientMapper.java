package com.traore.stockmanagement.mapper;

import com.traore.stockmanagement.dto.CommandLineClient.CommandLineClientDTO;
import com.traore.stockmanagement.model.CommandLineClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandLineClientMapper {

    public CommandLineClientDTO fromEntity(CommandLineClient commandLineClient){
        if(commandLineClient != null){
            return CommandLineClientDTO.builder()
                    .id(commandLineClient.getId())
                    .unitPrice(commandLineClient.getUnitPrice())
                    .quantity(commandLineClient.getQuantity())
                    .product(
                            ProductMapper.fromEntityStatic(
                                    commandLineClient.getProduct()
                            )
                    )
                    .commandClient(
                            CommandClientMapper.fromEntityToCommandClientOnlyDTO(
                                    commandLineClient.getCommandClient()
                            )
                    )
                    .build();
        }

        return null;
    }
    public CommandLineClient toEntity(CommandLineClientDTO dto){
        if(dto != null){
            return CommandLineClient.builder()
                    .unitPrice(dto.getUnitPrice())
                    .quantity(dto.getQuantity())
                    .product(
                            ProductMapper.fromProductDTOToEntity(
                                    dto.getProduct()
                            )
                    )
                    .commandClient(
                            CommandClientMapper.fromCommandClientOnlyToEntity(
                                    dto.getCommandClient()
                            )
                    )
                    .build();
        }

        return null;
    }

    public static CommandLineClientDTO fromEntityStatic(CommandLineClient commandLineClient){
        if(commandLineClient != null){
            return CommandLineClientDTO.builder()
                    .id(commandLineClient.getId())
                    .unitPrice(commandLineClient.getUnitPrice())
                    .quantity(commandLineClient.getQuantity())
                    .product(
                            ProductMapper.fromEntityStatic(
                                    commandLineClient.getProduct()
                            )
                    )
                    .build();
        }

        return null;
    }

    public static List<CommandLineClientDTO> fromEntity(List<CommandLineClient> commandLineClients){
        if(commandLineClients != null){
            List<CommandLineClientDTO> dtos = new ArrayList<>();
            for (CommandLineClient commandLineProvider : commandLineClients){
                dtos.add(
                        fromEntityStatic(commandLineProvider)
                );
            }
            return dtos;
        }
        return null;
    }
}
